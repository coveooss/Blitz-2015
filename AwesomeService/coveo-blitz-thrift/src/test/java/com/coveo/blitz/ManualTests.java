package com.coveo.blitz;

import com.coveo.blitz.thrift.AwesomeService;
import com.coveo.blitz.thrift.OrderingType;
import com.coveo.blitz.thrift.Request;
import com.coveo.blitz.thrift.Response;
import com.coveo.blitz.thrift.Sort;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ManualTests
{
    @Test
    @Ignore("Manual test for testing binary protocol")
    public void testManualBinaryConnection() throws TException, InterruptedException
    {
        TTransport transport = new TSocket("localhost", 9090);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        AwesomeService.Client client = new AwesomeService.Client(protocol);

        System.out.println("Calling ping");
        System.out.println(client.ping());

        System.out.println("Calling reset");
        client.reset();
        System.out.println("worked");

        System.out.println("Calling getData");
        Response response = client.getData(new Request(new ArrayList<>(),
                                                       new ArrayList<>(),
                                                       new ArrayList<>(),
                                                       Arrays.asList(new Sort(
                                                               "DATE",
                                                               "COUNT",
                                                               OrderingType.ASCENDING))));
        System.out.println(response);

        System.out.println("Calling handleMapReduceResult");
        client.handleMapReduceResult("test1", ByteBuffer.wrap(RandomStringUtils.random(5120).getBytes()));
        System.out.println("worked");

        System.out.println("Closing transport.");
        transport.close();
    }

    @Test
    public void gsimard()
    {
        String key = "allo/some/path/file.txt";
        System.out.println(key.substring(key.lastIndexOf("/") + 1));
    }
}
