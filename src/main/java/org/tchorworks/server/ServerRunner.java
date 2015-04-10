package org.tchorworks.server;

import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class ServerRunner {

    private static final Logger LOGGER = Logger.getLogger(ServerRunner.class);

    private static final String SERVER_INITIALIZER_PROPERTY = "serverInitializer";

    private static Thread serverThread;

    private static Server server;


    public static void main(String[] args) {
        installSignalHandlers();
        registerShutdownHook();

        server = new Server();

        String className = System.getProperty(SERVER_INITIALIZER_PROPERTY);

        if (className == null) {
            LOGGER.error("No initializer classname provided. Set -D" + SERVER_INITIALIZER_PROPERTY + "=fullyQualifiedClassName");
            return;
        }

        ServerInitializer initializer = null;

        try {
            Class c = Class.forName(className);
            Constructor<ServerInitializer> constructor = c.getConstructor();
            initializer = constructor.newInstance();
        } catch (NoSuchMethodException | ClassNotFoundException | ClassCastException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            LOGGER.error("Invalid initializer provided.", e);
        }

        if (initializer != null) {
            initializer.initServer(server);

            serverThread = new Thread(server, "Server");
            serverThread.start();
        }
    }


    static void shutdownServer() {
        if (serverThread == null) {
            return;
        }

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
