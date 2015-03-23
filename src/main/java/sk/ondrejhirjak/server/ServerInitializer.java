package sk.ondrejhirjak.server;


/**
 * Server initializer used to add required {@link sk.ondrejhirjak.module.ServerModule} instances.
 *
 * @author Ondrej Hirjak
 */
public interface ServerInitializer {

    void initServer(Server server);
}
