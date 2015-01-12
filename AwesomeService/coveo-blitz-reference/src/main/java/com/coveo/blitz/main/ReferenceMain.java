package com.coveo.blitz.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServlet;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coveo.blitz.reference.ReferenceServer;
import com.coveo.blitz.thrift.AwesomeService;
import com.thetransactioncompany.cors.CORSFilter;

public class ReferenceMain
{
    private static final Logger logger = LoggerFactory.getLogger(ReferenceMain.class);

    private static class ServerRunner implements Runnable
    {
        private TServer server;

        public ServerRunner(TServer server)
        {
            this.server = server;
        }

        @Override
        public void run()
        {
            server.serve();
        }
    }

    private List<Runnable> joinMethods = new ArrayList<>();

    public static void main(String[] args) throws Exception
    {
        ReferenceMain main = new ReferenceMain();
        main.startServer1();
        main.startServer2();
        main.join();
    }

    private ReferenceServer referenceServer;

    public ReferenceMain() throws IOException
    {
        referenceServer = new ReferenceServer();
        if (System.getProperty("listen") == null) {
            String filePath = System.getProperty("file");
            if (filePath == null) {
                throw new RuntimeException("Parameter 'file' is missing. Use -Dfile=path/to/file.json, -Dfile=path/to/folder/ or use -Dlisten if you want to wait for files from the server.");
            }

            if (filePath.endsWith("json")) {
                referenceServer.loadDataFromJson(Paths.get(filePath));
            } else if (filePath.endsWith("/")) {
                Files.list(Paths.get(filePath)).forEach(referenceServer::loadDataFromJson);
            }
        }
    }

    public void join()
    {
        joinMethods.forEach(Runnable::run);
    }

    public void startServer1() throws Exception
    {
        Server server = new Server(9091);
        ServletHandler handler = new ServletHandler();

        AwesomeService.Processor<AwesomeService.Iface> processor = new AwesomeService.Processor<>(referenceServer);
        ServletHolder holder = new ServletHolder(new TServlet(processor, new TJSONProtocol.Factory()));
        handler.addFilterWithMapping(CORSFilter.class, "/*", 0);
        handler.addServletWithMapping(holder, "/*");
        server.setHandler(handler);
        server.start();
        logger.info("Started JSON interface.");

        joinMethods.add(() -> {
            try {
                server.join();
            } catch (InterruptedException ignored) {
            }
        });
    }

    public void startServer2() throws Exception
    {
        AwesomeService.Processor<AwesomeService.Iface> processor = new AwesomeService.Processor<>(referenceServer);
        TServerTransport serverTransport = new TServerSocket(9090);
        TServer server = new TSimpleServer(new TSimpleServer.Args(serverTransport).processor(processor));

        ServerRunner serverRunner = new ServerRunner(server);
        Thread serverThread = new Thread(serverRunner);

        serverThread.start();
        logger.info("Started binary interface");

        joinMethods.add(() -> {
            try {
                serverThread.join();
            } catch (InterruptedException ignored) {
            }
        });
    }
}
