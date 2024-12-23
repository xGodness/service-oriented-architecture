package ru.xgodness;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.xgodness.endpoint.enums.EnumsClient;
import ru.xgodness.endpoint.faculties.FacultiesClient;
import ru.xgodness.endpoint.labworks.LabworksClient;
import ru.xgodness.endpoint.labworks.model.dto.Difficulty;
import ru.xgodness.endpoint.minimalpoints.MinimalPointsClient;
import ru.xgodness.endpoint.ping.PingClient;
import ru.xgodness.model.dto.util.DifficultyDeserializer;

@Configuration
public class ApplicationConfiguration {
    public static final String WS_URI = "http://labworks-service:5268/labworks-service/api/v1/ws/";
    private static final int HTTP_PORT = 5168;

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
        factory.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "|{}[]"));
        return factory;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("ru.xgodness.endpoint");
        return marshaller;
    }

    @Bean
    public EnumsClient enumsClient(Jaxb2Marshaller marshaller) {
        EnumsClient client = new EnumsClient();
        client.setDefaultUri(WS_URI);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public FacultiesClient facultiesClient(Jaxb2Marshaller marshaller) {
        FacultiesClient client = new FacultiesClient();
        client.setDefaultUri(WS_URI);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public LabworksClient labworksClient(Jaxb2Marshaller marshaller) {
        LabworksClient client = new LabworksClient();
        client.setDefaultUri(WS_URI);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public MinimalPointsClient minimalPointsClient(Jaxb2Marshaller marshaller) {
        MinimalPointsClient client = new MinimalPointsClient();
        client.setDefaultUri(WS_URI);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public PingClient pingClient(Jaxb2Marshaller marshaller) {
        PingClient client = new PingClient();
        client.setDefaultUri(WS_URI);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
