package ru.xgodness;

import org.jooq.codegen.GenerationTool;

public class JooqGenerator {
    private static final boolean GENERATE_FROM_HELIOS = false;

    public static void main(String[] args) {
        try {
            String configString = new ResourceExtractor().readResource(
                    GENERATE_FROM_HELIOS ? "jooq-config-helios.xml" : "jooq-config-local.xml");
            GenerationTool.generate(configString);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
