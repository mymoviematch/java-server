package sk.ondrejhirjak.jserver.example;

import sk.ondrejhirjak.jserver.server.Server;
import sk.ondrejhirjak.jserver.server.ServerInitializer;


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
