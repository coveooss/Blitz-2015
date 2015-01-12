package com.coveo.blitz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.coveo.blitz.thrift.AwesomeService;
import com.coveo.blitz.thrift.Request;
import com.coveo.blitz.thrift.Response;
import com.google.common.collect.ImmutableList;

public class AnalyticsTest
{
    private static final int SERVER_PORT = 50091;

    private AwesomeService.Iface mockedServer;
    private ServerRunner serverRunner;
    private Thread serverThread;

    private class ServerRunner implements Runnable
    {
        private TServer server;
        private boolean isRunning;

        public boolean isRunning()
        {
            return isRunning;
        }

        public ServerRunner(TServer server)
        {
            this.server = server;
        }

        @Override
        public void run()
        {
            isRunning = true;
            server.serve();
        }

        public void stop()
        {
            server.stop();
            isRunning = false;
        }
    }

    @Before
    public void spawnServer() throws TException
    {
        mockedServer = Mockito.mock(AwesomeService.Iface.class);
        Mockito.when(mockedServer.getData(Mockito.any(Request.class))).thenReturn(new Response());

        AwesomeService.Processor<AwesomeService.Iface> processor = new AwesomeService.Processor<>(mockedServer);
        TServerTransport serverTransport = new TServerSocket(SERVER_PORT);
        final TServer server = new TSimpleServer(new TSimpleServer.Args(serverTransport).processor(processor));

        serverRunner = new ServerRunner(server);
        serverThread = new Thread(serverRunner);
        serverThread.setDaemon(true);
        serverThread.start();
    }

    @After
    public void tearDown() throws InterruptedException
    {
        if (serverRunner != null && serverRunner.isRunning()) {
            serverRunner.stop();
            serverThread.interrupt();
        }
    }

    @Test
    public void testLocalServerWithLocalClientCanDiscuss() throws TException
    {
        AwesomeService.Client indexerClient = createClient();
        Request request = new Request();
        request.setDimensions(ImmutableList.of("VERB"));
        indexerClient.getData(request);
        Mockito.verify(mockedServer).getData(request.deepCopy());
    }

    @Test
    public void testThousandsOfQueries() throws TException
    {
        long startTime = System.currentTimeMillis();

        AwesomeService.Client indexerClient = createClient();
        int nbOfQueries = 20000;
        for (int i = 0; i < nbOfQueries; i++) {
            Request request = new Request();
            indexerClient.getData(request);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Executed " + nbOfQueries + " queries in " + (endTime - startTime) + " ms.");
        Mockito.verify(mockedServer, Mockito.times(nbOfQueries)).getData(Mockito.any(Request.class));
    }
    
    @Test
    public void testForwardFile() throws TException
    {
        long startTime = System.currentTimeMillis();

        AwesomeService.Client indexerClient = createClient();
        String data = "patate";
        ByteBuffer bb = ByteBuffer.wrap(data.getBytes());
        
        indexerClient.handleMapReduceResult("part1", bb);
        
        long endTime = System.currentTimeMillis();

        System.out.println("Executed Foward file in " + (endTime - startTime) + " ms.");
        Mockito.verify(mockedServer).handleMapReduceResult("part1", bb.duplicate());
    }
    
    @Test
    public void testForwardBigFile() throws TException, IOException
    {
        long startTime = System.currentTimeMillis();

        AwesomeService.Client indexerClient = createClient();
        
        InputStream is;
        BufferedReader reader = null;
      
        try {
            is = ClassLoader.getSystemResourceAsStream("ForwardTest.txt");
            reader = new BufferedReader(new InputStreamReader(is));
            
            StringBuffer sb = new StringBuffer();
            String s;
            while ((s = reader.readLine()) != null) {
                sb.append(s);
            }
            
            String output = sb.toString();
            ByteBuffer bb = ByteBuffer.wrap(output.getBytes());
            
            indexerClient.handleMapReduceResult("part1", bb);
            Mockito.verify(mockedServer).handleMapReduceResult("part1", bb.duplicate());
        } finally {
            if (reader != null) {
            	reader.close();
            }
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Executed Forward Big file in " + (endTime - startTime) + " ms.");
        
    }

    private AwesomeService.Client createClient() throws TTransportException
    {
        TTransport transport = new TSocket("localhost", SERVER_PORT);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);

        return new AwesomeService.Client(protocol);
    }
}
