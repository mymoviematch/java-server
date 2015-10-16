package sk.ondrejhirjak.jserver.conf;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class ConfigurationLoaderTest {


    @Test
    public void test() {
        ConfigurationLoader confLoader = new ConfigurationLoader();
        confLoader.init();

        Configuration conf = confLoader.getConfiguration();

        assertThat(conf.dbDriver, is("db_driver"));
        assertThat(conf.dbUrl, is("jdbc:db_type:db_options"));
        assertThat(conf.dbUsername, is("db_username"));
        assertThat(conf.dbPassword, is("db_password"));
        assertThat(conf.dbAutoMigration, is(false));
        assertThat(conf.webHost, is("localhost"));
        assertThat(conf.webPort, is(8080));
    }
}
