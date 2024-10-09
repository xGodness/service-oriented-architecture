# How to deploy on Payara Micro 6
* Initialise Postgres database


* Build Postgres schema with `resources/sql/ddl-init.sql`:

``psql -U <postgres_user> -d <database_name> -f ddl-init.sql``

* Change Postgres credentials and JDBC url in `resources/jdbc.properties`


* If for some reason you need to regenerate JOOQ models, run:

``src/main/java/codegen/JooqGenerator.java``

* Build `war` with Maven:

``mvn clean validate compile package``

* Run with Payara Micro:

``java -jar ${PAYARA_JAR} --deploy labworks-service/target/labworks-service.war``