package com.mymoviematch.simpleserver.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mymoviematch.simpleserver.conf.ConfigurationLoader;
import com.mymoviematch.simpleserver.module.ServerModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Server implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Server.class);

    private static final long SLEEP_TIME = 10000;

    private ConfigurationLoader confLoader = new ConfigurationLoader();

    private List<ServerModule> modules = new ArrayList<>();

    private volatile boolean shutdown = false;

    private boolean initialized = false;


    @Override
    public void run() {
        init();
        start();
        loop();
        stop();
    }


    public void shutdown() {
        shutdown = true;
    }


    public void addModule(ServerModule module) {
        if (initialized) {
            throw new RuntimeException("Cannot add module, server already initialized.");
        }

        modules.add(module);
    }


    public void addModules(List<ServerModule> newModules) {
        if (initialized) {
            throw new RuntimeException("Cannot add modules, server already initialized.");
        }

        modules.addAll(newModules);
    }


    public void setConfigurationLoader(ConfigurationLoader confLoader) {
        this.confLoader = confLoader;
    }


    private void init() {
        confLoader.init();

        modules.forEach((m) -> m.init(confLoader.getConfiguration()));

        initialized = true;

        LOGGER.info("Server initialized.");
    }


    private void start() {
        modules.forEach((m) -> m.start());

        LOGGER.info("Server started.");
    }


    private void loop() {
        while (!shutdown) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                LOGGER.info("Server interrupted.");
            }
        }
    }


    private void stop() {
        List<ServerModule> modulesR = new ArrayList<>(modules);

        Collections.reverse(modulesR);
        modulesR.forEach((m) -> m.stop());

        LOGGER.info("Server stopped.");
    }
}
