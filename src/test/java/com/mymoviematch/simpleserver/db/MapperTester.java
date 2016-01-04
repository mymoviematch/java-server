package com.mymoviematch.simpleserver.db;

import org.junit.Before;
import com.mymoviematch.simpleserver.conf.ConfigurationLoader;


public class MapperTester {

    @Before
    public void setUp() {
        ConfigurationLoader confLoader = new ConfigurationLoader();
        confLoader.init();
        DbContext dbContext = new DbContext();
        dbContext.init(confLoader.getConfiguration());
    }
}
