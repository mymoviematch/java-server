package sk.ondrejhirjak.jserver.db;

import org.junit.Before;
import sk.ondrejhirjak.jserver.conf.ConfigurationLoader;


public class MapperTester {

    @Before
    public void setUp() {
        ConfigurationLoader confLoader = new ConfigurationLoader();
        confLoader.init();
        DbContext dbContext = new DbContext();
        dbContext.init(confLoader.getConfiguration());
    }
}
