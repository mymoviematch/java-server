package com.mymoviematch.simpleserver.module;

import com.mymoviematch.simpleserver.conf.Configuration;


/**
 * Generic server module interface with init, start and stop methods.
 *
 * @author Ondrej Hirjak
 */
public interface ServerModule {

    void init(Configuration configuration);

    void start();

    void stop();
}
