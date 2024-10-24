# How to deploy on WildFly (standalone mode)

* Build `war` with Maven:

```shell
mvn clean validate compile package
```


* Copy `bars-service/target/bars-service.war` to `$WILDFLY_HOME/standalone/deployments`:

```shell
cp bars-service/target/bars-service.war $WILDFLY_HOME/standalone/deployments
```


* Set `port-offset` in `$WILDFLY_HOME/standalone/configuration/standalone.xml` (default `http` and `https` ports are `8080` and `8443` respectfully):

```xml
<socket-binding-group name="standard-sockets"
    default-interface="public"
    port-offset="${jboss.socket.binding.port-offset:1234}">
```


* Run WildFly in standalone mode:

```shell
sh $WILDFLY_HOME/bin/standalone.sh
```