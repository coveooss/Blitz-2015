package com.coveo.blitz.reference;

import com.coveo.blitz.thrift.DataPoint;
import com.coveo.blitz.thrift.Request;
import com.coveo.blitz.thrift.Response;
import com.google.common.collect.ImmutableList;
import com.google.common.io.ByteStreams;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.fest.assertions.api.Assertions.*;

public class ReferenceServerTest
{
    private ReferenceServer server;

    @Before
    public void setup()
    {
        server = new ReferenceServer();
    }

    public void withSample01DataJson()
    {
        try {
            server.loadDataFromJson(Utils.getPath("sample_01.json"));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void queryJson() throws IOException, TException
    {
        withSample01DataJson();
        Request request = new Request();
        request.setDimensions(ImmutableList.of("VERB"));
        request.setMetrics(ImmutableList.of("COUNT"));
        Response response = server.getResponseFromJsonData(request);
        assertThat(response).isNotNull();
        assertThat(response.getData()).hasSize(4);
    }

    @Test
    public void testHandleResults() throws IOException, TException
    {
        Path filePath = Utils.getPath("sample_01.json");
        ByteBuffer byteBuffer = ByteBuffer.wrap(ByteStreams.toByteArray(Files.newInputStream(filePath)));
        server.handleMapReduceResult("sampletest", byteBuffer);
    }

    private static DataPoint getDataPointFromDimension(List<DataPoint> dataPoints, String type, String value)
    {
        for (DataPoint d : dataPoints) {
            if (d.getDimensions().get(type).equals(value)) {
                return d;
            }
        }
        return null;
    }
}
