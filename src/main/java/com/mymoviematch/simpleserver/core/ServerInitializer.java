package com.mymoviematch.simpleserver.core;


/**
 * Server initializer used to add required {@link com.mymoviematch.simpleserver.module.ServerModule} instances.
 *
 * @author Ondrej Hirjak
 */
public interface ServerInitializer {

    void initServer(Server server);
}
