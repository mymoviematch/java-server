package sk.ondrejhirjak.web;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import sk.ondrejhirjak.module.ServerModule;
import sk.ondrejhirjak.server.Configuration;
import sk.ondrejhirjak.web.exception.PersistenceExceptionMapper;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;


public class WebServer implements ServerModule {

    private static final Logger LOGGER = Logger.getLogger(WebServer.class);

    private String baseUri;

    private Set<Class<?>> classes = new HashSet<>();

    private HttpServer server;


    @Override
    public void init(Configuration configuration) {
        baseUri = "http://" + configuration.webHost + ":" + configuration.webPort + "/";
    }


    @Override
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


    @Override
    public void stop() {
        server.shutdownNow();

        // TODO hiro: Wait for server to shutdown
    }


    public String getBaseUri() {
        return baseUri;
    }


    public void addResource(Class resourceClass) {
        // TODO: fail to add classes after initialized
        classes.add(resourceClass);
    }


    public void addResources(Set<Class<?>> resourceClasses) {
        // TODO: fail to add classes after initialized
        classes.addAll(resourceClasses);
    }
}
