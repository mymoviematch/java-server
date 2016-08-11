package com.mymoviematch.simpleserver.core;


import com.mymoviematch.simpleserver.conf.Configuration;
import com.mymoviematch.simpleserver.module.ServerModule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class ServerTest {

    @Test
    public void testWorkflow() {
        Server server = new Server();

        ServerModuleTest module1 = new ServerModuleTest();
        List<ServerModuleTest> modules = new ArrayList<ServerModuleTest>() {{
            new ServerModuleTest();
            new ServerModuleTest();
        }};

        server.addModule(module1);
        server.addModules(modules);

        server.shutdown();

        server.run();

        assertThat(module1.wasInited, is(true));
        assertThat(module1.wasStarted, is(true));
        assertThat(module1.wasStopped, is(true));

        modules.forEach(m -> {
            assertThat(m.wasInited, is(true));
            assertThat(m.wasInited, is(true));
            assertThat(m.wasStopped, is(true));
        });
    }


    @Test(expected = ServerException.class)
    public void addModule() {
        Server server = new Server();

        server.shutdown();

        server.run();

        server.addModule(new ServerModuleTest());
    }


    @Test(expected = ServerException.class)
    public void addModules() {
        Server server = new Server();

        server.shutdown();

        server.run();

        server.addModules(new ArrayList<ServerModuleTest>() {{ new ServerModuleTest(); }});
    }


    private class ServerModuleTest implements ServerModule {

        boolean wasInited = false;

        boolean wasStarted = false;

        boolean wasStopped = false;

        @Override
        public void init(Configuration configuration) {
            wasInited = true;
        }


        @Override
        public void start() {
            wasStarted = true;
        }


        @Override
        public void stop() {
            wasStopped = true;
        }
    }
}
