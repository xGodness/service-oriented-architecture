# How to deploy on Payara Micro

* Initialise Postgres database:

```shell
psql -U ${POSTGRES_USER} -c 'create database <postgres_database>;'
```


* Build Postgres schema with `resources/sql/ddl-init.sql`:

```shell
psql -U ${POSTGRES_USER} -d ${POSTGRES_DATABASE} -f ddl-init.sql
```


* Change Postgres credentials and JDBC url in `resources/jdbc.properties`


* If for some reason you need to regenerate JOOQ models, run `src/main/java/codegen/JooqGenerator.java#main()`


* Build `war` with Maven:

```shell
mvn clean validate compile package
```


* Deploy with Payara Micro:

```shell
java -jar ${PAYARA_JAR} --port ${PAYARA_PORT} --deploy labworks-service/target/labworks-service.war
```