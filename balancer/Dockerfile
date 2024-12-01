FROM alpine:latest

RUN apk add --no-cache haproxy python3 py3-requests

COPY ./balancer/haproxy.cfg /usr/local/etc/haproxy/haproxy.cfg
COPY ./balancer/haproxy-ssl.pem /usr/local/etc/haproxy/haproxy-ssl.pem
COPY ./balancer/cors.lua /usr/local/etc/haproxy/cors.lua
COPY ./balancer/errors/429.http /usr/local/etc/haproxy/429.http
COPY ./balancer/errors/503.http /usr/local/etc/haproxy/503.http

COPY ./balancer/update-service-list.py /usr/local/etc/haproxy/update-service-list.py

RUN touch /usr/local/etc/haproxy/last-daemon-pid.txt

CMD ["sh", "-c", "haproxy -f /usr/local/etc/haproxy/haproxy.cfg -D -p /usr/local/etc/haproxy/last-daemon-pid.txt & python3 /usr/local/etc/haproxy/update-service-list.py"]