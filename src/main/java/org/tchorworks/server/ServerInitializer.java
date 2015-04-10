package org.tchorworks.server;


/**
 * Server initializer used to add required {@link org.tchorworks.module.ServerModule} instances.
 *
 * @author Ondrej Hirjak
 */
public interface ServerInitializer {

    void initServer(Server server);
}
