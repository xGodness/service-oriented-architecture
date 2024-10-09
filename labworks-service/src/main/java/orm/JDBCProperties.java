package orm;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

public class JDBCProperties {
    private static final String PROPERTIES_RELATIVE_PATH = "../applications/labworks-service/WEB-INF/classes/jdbc.properties";

    @Getter
    private static final String username;
    @Getter
    private static final String password;
    @Getter
    private static final String url;

    static {
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        String fullPropertiesPath = rootPath + PROPERTIES_RELATIVE_PATH;

        Properties properties = new Properties();

        try {
            properties.load(Files.newInputStream(Path.of(fullPropertiesPath)));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        username = properties.getProperty("username");
        password = properties.getProperty("password");
        url = properties.getProperty("url");
    }
}
