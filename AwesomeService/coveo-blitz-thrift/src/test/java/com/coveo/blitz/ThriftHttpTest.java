package com.coveo.blitz;

import static org.fest.assertions.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.apache.http.client.utils.URIBuilder;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServlet;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.coveo.blitz.thrift.AwesomeService;
import com.coveo.blitz.thrift.DataPoint;
import com.coveo.blitz.thrift.Request;
import com.coveo.blitz.thrift.Response;

public class ThriftHttpTest
{
    private static final int SERVER_PORT = 50091;

    private AwesomeService.Iface mockedServer;
    private Server server;

    @Before
    public void spawnServer() throws Exception
    {
        server = new Server(SERVER_PORT);
        ServletHandler handler = new ServletHandler();

        mockedServer = Mockito.mock(AwesomeService.Iface.class);
        when(mockedServer.getData(any(Request.class))).thenReturn(new Response(Arrays.asList(new DataPoint(),
                                                                                             new DataPoint())));

        AwesomeService.Processor<AwesomeService.Iface> processor = new AwesomeService.Processor<>(mockedServer);
        ServletHolder holder = new ServletHolder(new TServlet(processor, new TJSONProtocol.Factory()));

        handler.addServletWithMapping(holder, "/*");
        server.setHandler(handler);

        server.start();
    }

    @After
    public void tearDown() throws Exception
    {
        if (server != null && server.isRunning()) {
            server.stop();
        }
    }

    @Test
    public void testHttp() throws Exception
    {
        Request request = new Request();
        Response r = createClient().getData(request);
        assertThat(r.getData()).hasSize(2);
        Mockito.verify(mockedServer).getData(request.deepCopy());
    }

    private AwesomeService.Client createClient() throws TTransportException
    {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("localhost");
        uriBuilder.setPort(SERVER_PORT);

        TTransport transport = new THttpClient(uriBuilder.toString());
        transport.open();
        TProtocol protocol = new TJSONProtocol(transport);

        return new AwesomeService.Client(protocol);
    }
}
