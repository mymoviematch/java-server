package org.tchorworks.web;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.tchorworks.conf.Configuration;
import org.tchorworks.module.ServerModule;
import org.tchorworks.web.exception.GenericExceptionMapper;

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
        ResourceConfig rc = new ResourceConfig();

        rc.registerClasses(JacksonFeature.class);
        rc.registerClasses(GenericExceptionMapper.class);

        if (classes != null) {
            rc.registerClasses(classes);
        }

        server = GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), rc);

        LOGGER.debug(String.format("Jersey app started with WADL available at %sapplication.wadl", baseUri));
    }


    @Override
    public void stop() {
        server.shutdownNow();

        // TODO: Wait for server to shutdown
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
