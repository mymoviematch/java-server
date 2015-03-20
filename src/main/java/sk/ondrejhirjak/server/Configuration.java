package sk.ondrejhirjak.server;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Configuration {

    private static final Logger LOGGER = Logger.getLogger(Configuration.class);

    private static final String DEFAULT_PROPERTY_FILE = "/default.properties";

    private static final String CONFIG_FILE_PROPERTY = "configFile";

    public String dbUrl;

    public String dbUser;

    public String dbPass;

    public boolean dbAutoMigration;

    public String webHost;

    public int webPort;


    public void init() {
        Properties defaults = new Properties();
        Properties config = new Properties();

        try {
            InputStream inputStream = getClass().getResourceAsStream(DEFAULT_PROPERTY_FILE);
            defaults.load(inputStream);

            String fileName = System.getProperty(CONFIG_FILE_PROPERTY);

            if (fileName != null) {
                LOGGER.info("Using properties file: " + fileName);

                FileInputStream fileInputStream = new FileInputStream(fileName);
                config.load(fileInputStream);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found", e);
        } catch (IOException e) {
            LOGGER.error("I/O Error", e);
        }

        dbUrl = getStringProperty(defaults, config, "db.url");
        dbUser = getStringProperty(defaults, config, "db.username");
        dbPass = getStringProperty(defaults, config, "db.password");
        dbAutoMigration = getBooleanProperty(defaults, config, "db.auto_migration");
        webHost = getStringProperty(defaults, config, "web.host");
        webPort = getIntProperty(defaults, config, "web.port");
    }


    private String getStringProperty(Properties defaults, Properties config, String propertyName) {
        String value = config.getProperty(propertyName, "");

        if (value.isEmpty()) {
            value = defaults.getProperty(propertyName);
        }

        return value;
    }


    private int getIntProperty(Properties defaults, Properties config, String propertyName) {
        String value = config.getProperty(propertyName, "");

        if (!value.isEmpty()) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                LOGGER.error(e);
            }
        }

        return Integer.parseInt(defaults.getProperty(propertyName));
    }


    private boolean getBooleanProperty(Properties defaults, Properties config, String propertyName) {
        String value = config.getProperty(propertyName, "");

        if ("true".equals(value)) {
            return true;
        } else if ("false".equals(value)) {
            return false;
        }

        return Boolean.parseBoolean(defaults.getProperty(propertyName));
    }
}
