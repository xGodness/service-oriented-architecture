# What changed since 3-lab

* `labworks-service` is using SOAP now instead of REST; `labworks-rest` is a REST-gateway.
* `Mule ESB` was added as `mule-esb` module.
* `bars-ejb` uses `Mule` to communicate with the `labworks-service`.

# How to start

```shell
docker compose up
```