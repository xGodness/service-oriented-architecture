package codegen;

import org.jooq.codegen.GenerationTool;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class JooqGenerator {
    public static void main(String[] args) {
        try {
            String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
            GenerationTool.generate(Files.readString(Path.of(rootPath + "jooq-config.xml")));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
