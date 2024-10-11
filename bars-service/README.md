# How to deploy on WildFly (standalone mode)

* Build `war` with Maven:

```shell
mvn clean validate compile package
```


* Copy `bars-service/target/bars-service.war` to `${WILDFLY_HOME}/standalone/deployments`


* Set `port-offset` in `${WILDFLY_HOME}/standalone/configuration/standalone.xml` (default port is `8080`):

```xml
<socket-binding-group name="standard-sockets"
    default-interface="public"
    port-offset="${jboss.socket.binding.port-offset:1234}">
```


* Run WildFly in standalone mode:

```shell
sh ${WILDFLY_HOME}/bin/standalone.sh
```