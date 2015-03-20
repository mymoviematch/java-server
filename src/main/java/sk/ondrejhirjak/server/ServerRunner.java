package sk.ondrejhirjak.server;

import org.apache.log4j.Logger;


public class ServerRunner {

    private static final Logger LOGGER = Logger.getLogger(ServerRunner.class);

    private static Thread serverThread;

    private static Server server;


    public static void main(String[] args) {
        installSignalHandlers();
        registerShutdownHook();

        server = new Server();
        serverThread = new Thread(server, "Server");
        serverThread.start();
    }


    public static void shutdownServer() {
        server.shutdown();
        serverThread.interrupt();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            LOGGER.debug(e);
        }
    }


    private static void installSignalHandlers() {
        ServerSignalHandler signalHandler = new ServerSignalHandler("INT");
        signalHandler.installHandler();
    }


    private static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutdownServer();
            }
        });
    }
}
