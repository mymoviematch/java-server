package org.tchorworks.example;

import org.tchorworks.server.Server;
import org.tchorworks.server.ServerInitializer;


/**
 * Empty server initializer with no modules.
 *
 * @author Ondrej Hirjak
 */
public class EmptyInitializer implements ServerInitializer {

    @Override
    public void initServer(Server server) {
    }
}
