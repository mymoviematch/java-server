package sk.ondrejhirjak.conf;

import java.util.Properties;
import java.util.StringTokenizer;


public class Utils {

    public static String javanifyKey(String propertyKey) {
        StringTokenizer tokenizer = new StringTokenizer(propertyKey, "._-");

        StringBuilder builder = new StringBuilder();

        builder.append(tokenizer.nextToken());

        while (tokenizer.hasMoreTokens()) {
            builder.append(upperCaseFirstLetter(tokenizer.nextToken()));
        }

        return builder.toString();
    }


    public static String upperCaseFirstLetter(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }


    public static String getStringProperty(Properties defaults, Properties config, String propertyName) {
        String value = config.getProperty(propertyName, "");

        if (value.isEmpty()) {
            value = defaults.getProperty(propertyName);
        }

        return value;
    }


    public static int getIntProperty(Properties defaults, Properties config, String propertyName) {
        String value = config.getProperty(propertyName, "");

        if (!value.isEmpty()) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
            }
        }

        return Integer.parseInt(defaults.getProperty(propertyName));
    }


    public static boolean getBooleanProperty(Properties defaults, Properties config, String propertyName) {
        String value = config.getProperty(propertyName, "");

        if ("true".equals(value)) {
            return true;
        } else if ("false".equals(value)) {
            return false;
        }

        return Boolean.parseBoolean(defaults.getProperty(propertyName));
    }}
