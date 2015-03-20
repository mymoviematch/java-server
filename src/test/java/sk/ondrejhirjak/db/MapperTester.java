package sk.ondrejhirjak.db;

import org.junit.Before;
import sk.ondrejhirjak.server.Configuration;


public class MapperTester {

    @Before
    public void setUp() {
        Configuration configuration = new Configuration();
        configuration.init();
        DbContext dbContext = new DbContext();
        dbContext.init(configuration);
    }
}
