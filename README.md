# What changed since 2-lab

* `labworks-service` is using Spring Boot with embedded Tomcat now.
* `bars-service` is split in two modules: `bars-ejb` that encapsulates all business logic and made with EJB, and
  `bars-service` that is just web-interface that delegates all computations to `bars-ejb`.
* Now everything is deployed with Docker using `docker compose`.
* HAProxy was added as a balancer for `labworks-service` and `bars-service`.
* Consul was added as a service discovery mechanism for `labworks-service` instances.

# What's important

* Now all requests being handled by HAProxy balancer, thus all requests regardless of the target service should be
  addressed to the `4444` port.
* If you want to rebuild docker service, use `docker compose up build --no-cache <service-name>`.
* Anything that was mentioned in the 2-lab's README.md and was not mentioned here presumably remained unchanged.

# How to start

```shell
docker compose up
```