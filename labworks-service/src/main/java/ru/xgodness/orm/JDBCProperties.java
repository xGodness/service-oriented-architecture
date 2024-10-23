package ru.xgodness.orm;

import lombok.Getter;
import ru.xgodness.util.ResourceExtractor;

import java.io.IOException;
import java.util.Properties;

public class JDBCProperties {
    /* read system property set to JVM by `-DconnectToHelios=[true|false]` flag */
    private static final boolean CONNECT_TO_HELIOS = Boolean.getBoolean("connectToHelios");
    private static final String PROPERTIES_FILENAME = CONNECT_TO_HELIOS ? "jdbc-helios.properties" : "jdbc-local.properties";

    @Getter
    private static final String username;
    @Getter
    private static final String password;
    @Getter
    private static final String url;

    static {
        Properties properties = new Properties();

        try {
            properties.load(
                    new ResourceExtractor().readResourceAsInputStream(PROPERTIES_FILENAME)
            );
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        username = properties.getProperty("username");
        password = properties.getProperty("password");
        url = properties.getProperty("url");
    }
}
