package org.globant.configuration;

import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigurationManager.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (Exception e) {
            throw new RuntimeException("No se pudo cargar el archivo config.properties", e);
        }
    }

    public static String getBaseUrl() {
        return System.getProperty("base.url", properties.getProperty("base.url"));
    }
}
