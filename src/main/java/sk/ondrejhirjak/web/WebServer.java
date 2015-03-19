package sk.ondrejhirjak.web;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import sk.ondrejhirjak.main.Configuration;
import sk.ondrejhirjak.web.exception.PersistenceExceptionMapper;

import java.net.URI;
import java.util.Set;


public class WebServer {

    private static final Logger LOGGER = Logger.getLogger(WebServer.class);

    private String baseUri;

    private Set<Class<?>> classes;

    private HttpServer server;


    public void init(Configuration configuration) {
        baseUri = "http://" + configuration.webHost + ":" + configuration.webPort + "/";
    }


    public void start() {
        // TODO hiro: Log grizzly to log file

        final ResourceConfig rc = new ResourceConfig();
        rc.registerClasses(JacksonFeature.class);
        rc.registerClasses(PersistenceExceptionMapper.class);

        if (classes != null) {
            rc.registerClasses(classes);
        }

        server = GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), rc);

        LOGGER.debug(String.format("Jersey app started with WADL available at %sapplication.wadl", baseUri));
    }


    public void stop() {
        server.shutdownNow();

        // TODO hiro: Wait for server to shutdown
    }


    public String getBaseUri() {
        return baseUri;
    }


    public void setClasses(Set<Class<?>> classes) {
        this.classes = classes;
    }
}
