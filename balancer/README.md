# How to generate SSL key and certificate for HAProxy

```shell
openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -sha256 -days 3650 -nodes -subj "/C=RU/ST=./L=./O=./OU=./CN=localhost"
cat key.pem cert.pem > haproxy-ssl.pem
rm key.pem cert.pem
```