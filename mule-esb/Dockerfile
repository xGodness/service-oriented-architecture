FROM openjdk:8-jdk

RUN apt-get update && apt-get install -y unzip && apt-get clean

ENV MULE_HOME=/opt/mule
ENV MULE_VERSION=4.8.1
ENV MULE_ZIP=mule-ee-distribution-standalone-$MULE_VERSION.zip

RUN mkdir -p $MULE_HOME

COPY ./mule-esb/$MULE_ZIP /tmp/$MULE_ZIP

RUN unzip /tmp/$MULE_ZIP -d /opt/mule  \
    && \
    mv /opt/mule/mule-enterprise-standalone-4.8.1/* /opt/mule/ && \
    rmdir /opt/mule/mule-enterprise-standalone-4.8.1 && \
    rm /tmp/$MULE_ZIP

RUN chmod +x -R $MULE_HOME

COPY ./mule-esb/target/mule-esb-1.0.0-mule-application.jar $MULE_HOME/apps/

WORKDIR $MULE_HOME

CMD ["bin/mule"]
