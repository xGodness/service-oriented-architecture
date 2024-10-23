package ru.xgodness;

import org.jooq.codegen.GenerationTool;
import ru.xgodness.util.ResourceExtractor;

public class JooqGenerator {
    /* read system property set to JVM by `-DgenerateFromHelios=[true|false]` flag */
    private static final boolean GENERATE_FROM_HELIOS = Boolean.getBoolean("generateFromHelios");

    public static void main(String[] args) {
        try {
            String configString = new ResourceExtractor().readResourceAsString(
                    GENERATE_FROM_HELIOS ? "jooq-config-helios.xml" : "jooq-config-local.xml");
            GenerationTool.generate(configString);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
