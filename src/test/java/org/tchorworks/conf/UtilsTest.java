package org.tchorworks.conf;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class UtilsTest {

    @Test
    public void javanifyKey() {
        assertThat(Utils.javanifyKey("db"), is("db"));
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
}
