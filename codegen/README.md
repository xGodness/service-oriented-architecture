# How to generate JOOQ model

## Generate from local database

* Set `user` and `password` values to match your local Postgres database credentials in [
  `src/main/resources/jooq-config-local.xml`](src/main/resources/jooq-config-local.xml) file (make sure that database
  `soa` exists beforehand)


* Run [`src/main/java/codegen/ru/xgodness/JooqGenerator.java`](src/main/java/ru/xgodness/JooqGenerator.java)

## Generate from Helios database

* Set `user` and `password` values to match your Helios Postgres database credentials in [
  `src/main/resources/jooq-config-helios.xml`](src/main/resources/jooq-config-local.xml) file


* Setup port forwarding:

```shell
ssh -p 2222 -L 5433:localhost:5432 sXXXXXX@helios.se.ifmo.ru
```

* Run [`src/main/java/codegen/ru/xgodness/JooqGenerator.java`](src/main/java/ru/xgodness/JooqGenerator.java) with JVM
  option `-DgenerateFromHelios=true`

## Register artifact

After building `.jar` you may need to register it in local maven repository. To do so, run:

```shell
rm -r ~/.m2/repository/ru/xgodness/codegen;
mvn install:install-file -Dfile=codegen/target/codegen.jar -DgroupId=ru.xgodness -DartifactId=codegen -Dversion=1.0 -Dpackaging=jar
```