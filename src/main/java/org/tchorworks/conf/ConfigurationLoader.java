package org.tchorworks.conf;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;


public class ConfigurationLoader {

    private static final Logger LOGGER = Logger.getLogger(ConfigurationLoader.class);

    private static final String DEFAULT_PROPERTY_FILE = "/default.properties";

    private static final String CONFIG_FILE_PROPERTY = "configFile";

    private String propertyFile;

    private Configuration configuration;


    public ConfigurationLoader(String propertyFile, Configuration configuration) {
        this.propertyFile = propertyFile;
        this.configuration = configuration;
    }


    public ConfigurationLoader(String propertyFile) {
        this(propertyFile, new Configuration());
    }


    public ConfigurationLoader() {
        this(DEFAULT_PROPERTY_FILE);
    }


    public void init() {
        Properties defaults = new Properties();
        Properties config = new Properties();

        try {
            InputStream inputStream;

            inputStream = ClassLoader.getSystemResourceAsStream(propertyFile);

            if (inputStream == null) {
                inputStream = ConfigurationLoader.class.getResourceAsStream(propertyFile);
            }

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

        initConfiguration(defaults, config);
    }


    public Configuration getConfiguration() {
        return configuration;
    }


    private void initConfiguration(Properties defaults, Properties config) {
        for (Map.Entry<Object, Object> item : defaults.entrySet()) {
            String key = (String) item.getKey();
            String javaKey = Utils.javanifyKey(key);

            try {
                Field javaField = configuration.getClass().getField(javaKey);

                if (javaField.getType() == String.class) {
                    javaField.set(configuration, Utils.getStringProperty(defaults, config, key));
                } else if (javaField.getType() == int.class) {
                    javaField.set(configuration, Utils.getIntProperty(defaults, config, key));
                } else if (javaField.getType() == boolean.class) {
                    javaField.set(configuration, Utils.getBooleanProperty(defaults, config, key));
                }
            } catch (NoSuchFieldException e) {
                LOGGER.debug("No field " + javaKey + " for properties key " + key, e);
            } catch (IllegalAccessException e) {
                LOGGER.debug("Cannot set field " + javaKey + " for properties key " + key, e);
            }
        }
    }
}
