package org.tchorworks.db;

import org.junit.Before;
import org.tchorworks.conf.ConfigurationLoader;


public class MapperTester {

    @Before
    public void setUp() {
        ConfigurationLoader confLoader = new ConfigurationLoader();
        confLoader.init();
        DbContext dbContext = new DbContext();
        dbContext.init(confLoader.getConfiguration());
    }
}
