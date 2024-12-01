# How to generate SSL key and certificate for HAProxy

```shell
openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -sha256 -days 3650 -nodes -subj "/C=RU/ST=./L=./O=./OU=./CN=balancer"
cat key.pem cert.pem > haproxy-ssl.pem
rm key.pem cert.pem
```

# How to link HAProxy and Consul together

Apparently it requires some magic skills and time to make instances of Consul and HAProxy deployed in Docker interact
with each other properly. So a workaround was born -- `update-service-list.py`.

That's a small script that asks Consul about currently available instances of `labworks-service` and updates HAProxy's
config dynamically (kinda).

To be honest, this is a poor way to handle communication and it should've been handled with some proper configuration
and probably some magic (to make it work with Docker).

But if you're short on time this is your go to. 