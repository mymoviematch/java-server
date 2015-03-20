package sk.ondrejhirjak.example;

import sk.ondrejhirjak.server.Server;
import sk.ondrejhirjak.server.ServerInitializer;


/**
 * Empty server initializer with no modules.
 *
 * @author Ondrej Hirjak
 */
public class EmptyInitializer implements ServerInitializer {

    @Override
    public void addModules(Server server) {
    }
}
