package sk.ondrejhirjak.module;

import sk.ondrejhirjak.server.Configuration;


public interface ServerModule {

    void init(Configuration configuration);

    void start();

    void stop();
}
