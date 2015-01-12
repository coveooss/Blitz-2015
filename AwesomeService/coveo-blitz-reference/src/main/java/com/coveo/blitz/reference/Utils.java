package com.coveo.blitz.reference;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils
{

    public static Map<String, String> dimensionsToMap(Set<DimensionValue> dimensions)
    {
        return dimensions.stream().collect(Collectors.toMap(DimensionValue::getDimension, DimensionValue::getValue));
    }

    public static Map<String, Long> metricsToMap(Set<MetricValue> metrics)
    {
        return metrics.stream().collect(Collectors.toMap(MetricValue::getMetric, MetricValue::getValue));
    }

    public static Path getPath(String fileName)
    {
        try {
            return Paths.get(ClassLoader.getSystemResource(fileName).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static InputStream getResourceStream(String fileName)
    {
        return ClassLoader.getSystemResourceAsStream(fileName);
    }
}
