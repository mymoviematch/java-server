package sk.ondrejhirjak.main;

import org.apache.log4j.Logger;
import sk.ondrejhirjak.db.DbContext;
import sk.ondrejhirjak.service.ServiceContext;
import sk.ondrejhirjak.web.WebServer;


public class Server implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(Server.class);

    private WebServer webServer;

    private volatile boolean shutdown = false;


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


    private void init() {
        Configuration configuration = new Configuration();
        configuration.init();

        DbContext dbContext = new DbContext();
        dbContext.init(configuration);

        ServiceContext serviceContext = new ServiceContext();
        serviceContext.init();

        webServer = new WebServer();
        webServer.init(configuration);
    }


    private void start() {
        webServer.start();

        LOGGER.info("Server started.");
    }


    private void loop() {
        while (!shutdown) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                LOGGER.info("Server interrupted.");
            }
        }
    }


    private void stop() {
        webServer.stop();

        LOGGER.info("Server stopped.");
    }
}
