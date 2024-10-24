# How to deploy on Payara Micro

## Using local database

* Initialise Postgres database:

```shell
psql -U $POSTGRES_USER -c 'create database soa;'
```


* Build Postgres schema with `resources/sql/ddl-init.sql`:

```shell
psql -U $POSTGRES_USER -d $POSTGRES_DATABASE -f ddl-init.sql
```


* Change Postgres credentials and JDBC url in [`src/main/resources/jdbc-local.properties`](src/main/resources/jdbc-local.properties)


## Using Helios database

* Change Postgres credentials and JDBC url in [`src/main/resources/jdbc-helios.properties`](src/main/resources/jdbc-helios.properties)


* Setup port forwarding:

```shell
ssh -p 2222 -L 5433:localhost:5432 sXXXXXX@helios.se.ifmo.ru
```

## Regenerating JOOQ model

* If you want to regenerate JOOQ model, see [codegen/README.md](../codegen/README.md)


## Run

* Build `war` with Maven:

```shell
mvn clean validate compile package
```


* Deploy with Payara Micro:

```shell
java -jar $PAYARA_JAR --port $PAYARA_PORT --sslport $PAYARA_SSL_PORT --deploy labworks-service/target/labworks-service.war
```