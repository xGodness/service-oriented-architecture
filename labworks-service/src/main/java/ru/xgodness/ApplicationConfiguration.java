package ru.xgodness;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.xgodness.endpoint.labworks.model.dto.Difficulty;
import ru.xgodness.model.dto.util.DifficultyDeserializer;

@Configuration
public class ApplicationConfiguration {
    private final int HTTP_PORT = 5268;

    /* Add new connector to allow both http and https requests */
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
        return (TomcatServletWebServerFactory factory) -> {
            final Connector connector = new Connector();
            connector.setPort(HTTP_PORT);
            connector.setProperty("relaxedQueryChars", "|{}[]");
            factory.addAdditionalTomcatConnectors(connector);
        };
    }

    /* Registers custom `Difficulty` class deserializer */
    @Bean
    public Module withCustomDeserializers() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Difficulty.class, new DifficultyDeserializer());
        return module;
    }

    /* Fixes `Invalid character found in the request target` error when [ and ] are present in the request url */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                connector.setProperty("relaxedQueryChars", "|{}[]");
            }
        });
        return factory;
    }
}
