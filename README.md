# How to make it work

## [Deploying `labworks-service` on Payara Micro](bars-service/README.md)

## [Deploying `bars-service` on WildFly (standalone mode)](bars-service/README.md)

## Configuring truststore in WildFly

Since `bars-service` uses `labworks-service` as external API and `bars-service` allows only HTTPS requests, Payara's
self-signed certificate should be added to the WildFly's truststore.

1. Extract `MICRO-INF/domain/keystore.p12` from Payara Micro JAR-file.
2. Export `s1as` certificate from `keystore.p12`:
    ```shell
    keytool -exportcert -alias s1as -keystore keystore.p12 -storetype PKCS12 -file payara-s1as-cert.crt
    ```
3. Create new `wildfly-truststore.jks` and import `payara-s1as-cert` to it:
   ```shell
   keytool -import -alias payara-s1as-cert -file payara-s1as-cert.crt -keystore wildfly-truststore.jks -storepass changeit
   ```
4. Store `wildfly-truststore.jks` somewhere inside `${WILDFLY_HOME}` directory (just for convenience, it's optional):
   ```shell
   mv wildfly-truststore.jks ${WILDFLY_HOME}/standalone/configuration
   ```
5. Add `JAVA_OPTS` to the `${WILDFLY_HOME}/bin/standalone.conf` to tell WildFly where to find new truststore:
   ```
   JAVA_OPTS="$JAVA_OPTS -Djavax.net.ssl.trustStore=/absolute/path/to/wildfly/standalone/configuration/wildfly-truststore.jks -Djavax.net.ssl.trustStorePassword=changeit"
   ```

And you're all set.