package com.coveo.blitz.reference;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.coveo.blitz.thrift.DimensionFilter;
import com.coveo.blitz.thrift.OrderingType;
import com.coveo.blitz.thrift.Sort;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coveo.blitz.thrift.AwesomeService;
import com.coveo.blitz.thrift.DataPoint;
import com.coveo.blitz.thrift.Request;
import com.coveo.blitz.thrift.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

public class ReferenceServer implements AwesomeService.Iface
{
    private static final Logger logger = LoggerFactory.getLogger(ReferenceServer.class);

    private MapReduce jsonData;

    public ReferenceServer()
    {
        init();
    }

    public void init()
    {
        jsonData = new MapReduce();
    }

    public void loadDataFromJson(Path path)
    {
        try {
            loadDataFromJson(path.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadDataFromJson(File file) throws IOException
    {
        Gson gson = new Gson();
        MapReduce newData = gson.fromJson(new FileReader(file), MapReduce.class);
        // Merge MapReduce
        if (newData.values != null) {
            logger.info("Looking at " + newData.values.size() + " values.");
            mergeDimensionNameMaps(jsonData.values, newData.values);
        }

        logger.info("Loaded " + jsonData.values.size() + " values.");
    }

    private static void mergeDimensionNameMaps(
            Map<String, Map<String, MetricValues>> map1,
            Map<String, Map<String, MetricValues>> map2)
    {
        for (Map.Entry<String, Map<String, MetricValues>> kvp2 : map2.entrySet()) {
            Map<String, MetricValues> value1 = map1.get(kvp2.getKey());
            if (value1 != null) {
                // There is a conflict
                mergeDimensionValueMaps(value1, kvp2.getValue());
            } else {
                map1.put(kvp2.getKey(), kvp2.getValue());
            }
        }
    }

    private static void mergeDimensionValueMaps(
            Map<String, MetricValues> map1,
            Map<String, MetricValues> map2)
    {
        for (Map.Entry<String, MetricValues> kvp2 : map2.entrySet()) {
            MetricValues value1 = map1.get(kvp2.getKey());
            if (value1 != null) {
                mergeMetricValues(value1, kvp2.getValue());
            } else {
                map1.put(kvp2.getKey(), kvp2.getValue());
            }
        }
    }

    private static void mergeMetricValues(MetricValues map1, MetricValues map2)
    {
        for (Map.Entry<String, Double> kvp2 : map2.entrySet()) {
            Double value1 = map1.get(kvp2.getKey());
            if (value1 != null) {
                map1.put(kvp2.getKey(), mergeMetric(kvp2.getKey(), kvp2.getValue(), value1));
            } else {
                map1.put(kvp2.getKey(), kvp2.getValue());
            }
        }
    }

    @Override
    public Response getData(Request request) throws TException
    {
        logger.info("Get data! " + request);

        if (request == null) {
            return null;
        }

        List<String> requestedDimensions = request.getDimensions();
        if (requestedDimensions == null || requestedDimensions.isEmpty()) {
            return new Response();
        }

        List<String> requestedMetrics = request.getMetrics();
        if (requestedMetrics == null || requestedMetrics.isEmpty()) {
            return new Response();
        }

        Response result = getResponseFromJsonData(request);
        logger.info("Found {} results.",
                    result == null ? null : result.getData() == null ? null : result.getData().size());
        return result;
    }

    private static Comparator<Object> toStringCompareTo()
    {
        return (
                o1,
                o2) -> o1.toString().compareTo(o2.toString());
    }

    public Response getResponseFromJsonData(Request request) throws TException
    {
        List<String> requestedDimensions = request.getDimensions();
        List<String> requestedMetrics = request.getMetrics();

        if (requestedDimensions == null || requestedDimensions.isEmpty()) {
            return new Response();
        }

        if (requestedMetrics == null || requestedMetrics.isEmpty()) {
            return new Response();
        }

        List<String> filteringDimensions = new ArrayList<>();
        if (request.getFilters() != null) {
            filteringDimensions = request.getFilters()
                                         .stream()
                                         .filter(f -> f != null)
                                         .filter(f -> !requestedDimensions.contains(f.getDimension()))
                                         .map(DimensionFilter::getDimension)
                                         .collect(Collectors.toList());
        }

        List<String> sortedRequestedDimensions = Stream.concat(requestedDimensions.stream(),
                                                               filteringDimensions.stream())
                                                       .sorted(toStringCompareTo())
                                                       .collect(Collectors.toList());

        String dimensionKey = sortedRequestedDimensions.stream()
                                                       .map(Object::toString)
                                                       .map(String::toLowerCase)
                                                       .collect(Collectors.joining("|"));

        if (jsonData.values == null || jsonData.values.isEmpty()) {
            logger.warn("No data loaded! Returning nothing.");
            return new Response();
        }
        Map<String, MetricValues> metricsPerDimensions = jsonData.values.get(dimensionKey);

        if (metricsPerDimensions == null) {
            logger.warn("Didn't find dimension '" + dimensionKey + "'. Valid keys are " + jsonData.values.keySet()
                                + ".");
        }
        List<DataPoint> dataPoints = new ArrayList<>();

        if (metricsPerDimensions == null) {
            logger.warn("No data found for key " + dimensionKey);
            return new Response();
        }

        for (Map.Entry<String, MetricValues> metricPerDimension : metricsPerDimensions.entrySet()) {
            Map<String, String> dimensions = new HashMap<>();
            String[] keys = metricPerDimension.getKey().split("\\|");
            int i = 0;
            for (String dimensionType : sortedRequestedDimensions) {
                // We skip dimensions that are in sortedRequestedDimensions but not in requestedDimensions
                // Those dimensions are coming from filters.
                dimensions.put(dimensionType, keys[i]);
                i++;
            }

            boolean okay = request.getFilters() == null || request.getFilters().isEmpty();
            if (!okay) {
                okay = true;
                for (DimensionFilter filter : request.getFilters()) {
                    // If there's a single filter that doesn't match, we filter it out.
                    if (!dimensions.getOrDefault(filter.getDimension(), "").equals(filter.getValue())) {
                        okay = false;
                    }
                }
            }

            if (okay) {
                DataPoint p = new DataPoint();
                // These dimensions were added for filtering purposes. Don't report on them.
                filteringDimensions.forEach(dimensions::remove);
                p.setDimensions(dimensions);
                p.setMetrics(requestedMetrics.stream()
                                             .collect(Collectors.toMap(Function.identity(),
                                                                       m -> metricPerDimension.getValue()
                                                                                              .getOrDefault(m.toLowerCase(),
                                                                                                            -1.0)
                                                                                              .longValue())));

                dataPoints.add(p);
            }
        }

        List<Comparator<DataPoint>> comparators = new ArrayList<>();
        if (request.getSorts() != null) {
            for (Sort sort : request.getSorts()) {
                Comparator<DataPoint> comparator = null;
                if (sort.getDimension() != null) {
                    comparator = Comparator.comparing(d -> d.getDimensions().get(sort.getDimension()));
                } else if (sort.getMetric() != null) {
                    comparator = Comparator.comparing(d -> d.getMetrics().get(sort.getMetric()));
                }

                if (comparator != null && sort.getOrdering() == OrderingType.DESCENDING) {
                    comparator = comparator.reversed();
                }
                if (comparator != null) {
                    comparators.add(comparator);
                }
            }
        }

        Comparator<DataPoint> combinedComparator = null;
        for (Comparator<DataPoint> comparator : comparators) {
            if (combinedComparator == null) {
                combinedComparator = comparator;
            } else {
                combinedComparator = combinedComparator.thenComparing(comparator);
            }
        }
        if (combinedComparator != null) {
            dataPoints = dataPoints.stream().sorted(combinedComparator).collect(Collectors.toList());
        }

        // We only want 10 results.
        dataPoints = dataPoints.subList(0, Math.min(dataPoints.size(), 10));

        return new Response(dataPoints);
    }


    private static Double mergeMetric(String metricType, Double v1, Double v2)
    {
        switch (metricType) {
            case "lastrequesttimestamp":
                return Math.max(v1, v2);
            default:
                return v1 + v2;
        }
    }

    @Override
    public void reset() throws TException
    {
        logger.info("Reset!");
        init();
    }

    @Override
    public boolean ping() throws TException
    {
        logger.info("Ping!");
        return true;
    }

    @Override
    public void handleMapReduceResult(
            String name,
            ByteBuffer data) throws TException
    {
        logger.info("Receiving file {}", name);
        if (!data.hasRemaining()) {
            logger.warn("Ignoring empty file!");
            return;
        }

        Path fileName;
        try {
            fileName = Files.createTempFile(name, null);
        } catch (IOException ex) {
            logger.warn("Failed to create temp file");
            return;
        }

        byte[] dataToWrite = data.array();
        try (OutputStream out = Files.newOutputStream(fileName)) {
            out.write(dataToWrite);
        } catch (IOException e) {
            logger.warn("Failed to write file", e);
        }

        try {
            // loadDataFromCsv(fileName);
            loadDataFromJson(fileName);
        } catch (RuntimeException e) {
            logger.error("Failed to load data", e);
        }
    }
}
