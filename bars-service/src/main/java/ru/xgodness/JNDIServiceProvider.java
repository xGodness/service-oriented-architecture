package ru.xgodness;

import lombok.extern.java.Log;
import ru.xgodness.faculties.FacultyService;
import ru.xgodness.labworks.LabworkService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Log
public class JNDIServiceProvider {
    private static final int BARS_EJB_PORT = 9080;
    private static final String BARS_EJB_URL = "http-remoting://bars-ejb:%d".formatted(BARS_EJB_PORT);
    private static final String BARS_EJB_USERNAME = "user";
    private static final String BARS_EJB_PASSWORD = "password";

    private static final Context context;

    static {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, BARS_EJB_URL);
        jndiProperties.put(Context.SECURITY_PRINCIPAL, BARS_EJB_USERNAME);
        jndiProperties.put(Context.SECURITY_CREDENTIALS, BARS_EJB_PASSWORD);
        try {
            context = new InitialContext(jndiProperties);
        } catch (NamingException ex) {
            log.severe("NamingException occurred: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public static FacultyService getFacultyService() {
        try {
            return (FacultyService) context.lookup("ejb:/bars-ejb/FacultyService!ru.xgodness.faculties.FacultyService");
        } catch (NamingException ex) {
            log.severe("NamingException occurred: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public static LabworkService getLabworkService() {
        try {
            return (LabworkService) context.lookup("ejb:/bars-ejb/LabworkService!ru.xgodness.labworks.LabworkService");
        } catch (NamingException ex) {
            log.severe("NamingException occurred: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
}
