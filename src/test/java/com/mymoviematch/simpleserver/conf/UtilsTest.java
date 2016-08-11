package com.mymoviematch.simpleserver.conf;

import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class UtilsTest {

    @Test
    public void javanifyKey() {
        assertThat(Utils.javanifyKey("db"), is("db"));
        assertThat(Utils.javanifyKey("db.driver"), is("dbDriver"));
        assertThat(Utils.javanifyKey("db.url"), is("dbUrl"));
        assertThat(Utils.javanifyKey("db.auto_migration"), is("dbAutoMigration"));
        assertThat(Utils.javanifyKey("web.https-secure"), is("webHttpsSecure"));
    }


    @Test
    public void upperCaseFirstLetter() {
        assertThat(Utils.upperCaseFirstLetter("d"), is("D"));
        assertThat(Utils.upperCaseFirstLetter("db"), is("Db"));
        assertThat(Utils.upperCaseFirstLetter("dba"), is("Dba"));
    }


    @Test
    public void getStringProperty() {
        Properties defaults = new Properties();

        defaults.setProperty("key1", "value1");

        Properties props = new Properties();

        props.setProperty("key2", "value2");

        // Check missing value
        assertThat(Utils.getStringProperty(defaults, props, "key1"), is("value1"));
        // Check present value
        assertThat(Utils.getStringProperty(defaults, props, "key2"), is("value2"));
    }


    @Test
    public void getIntProperty() {
        Properties defaults = new Properties();

        defaults.setProperty("key1", "1");
        defaults.setProperty("key3", "3");

        Properties props = new Properties();

        props.setProperty("key2", "2");
        props.setProperty("key3", "three");

        // Check missing value
        assertThat(Utils.getIntProperty(defaults, props, "key1"), is(1));
        // Check present value
        assertThat(Utils.getIntProperty(defaults, props, "key2"), is(2));
        // Check incorrect value
        assertThat(Utils.getIntProperty(defaults, props, "key3"), is(3));
    }


    @Test
    public void getBooleanProperty() {
        Properties defaults = new Properties();

        defaults.setProperty("key1", "true");
        defaults.setProperty("key3", "false");

        Properties props = new Properties();

        props.setProperty("key2", "false");
        props.setProperty("key3", "very_false");

        // Check missing value
        assertThat(Utils.getBooleanProperty(defaults, props, "key1"), is(true));
        // Check present value
        assertThat(Utils.getBooleanProperty(defaults, props, "key2"), is(false));
        // Check incorrect value
        assertThat(Utils.getBooleanProperty(defaults, props, "key3"), is(false));
    }
}
