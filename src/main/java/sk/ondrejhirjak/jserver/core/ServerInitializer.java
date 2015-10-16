package sk.ondrejhirjak.jserver.core;


/**
 * Server initializer used to add required {@link sk.ondrejhirjak.jserver.module.ServerModule} instances.
 *
 * @author Ondrej Hirjak
 */
public interface ServerInitializer {

    void initServer(Server server);
}
