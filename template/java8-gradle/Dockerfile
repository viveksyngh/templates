FROM openjdk:8u121-jdk-alpine as builder

RUN apk --no-cache add curl \
    && echo "Pulling watchdog binary from Github." \
    && curl -sSL https://github.com/openfaas-incubator/of-watchdog/releases/download/0.2.5/of-watchdog > /usr/bin/fwatchdog \
    && chmod +x /usr/bin/fwatchdog \
    && apk del curl --no-cache

RUN addgroup -S app && adduser -S -g app app
RUN mkdir -p /home/app

WORKDIR /home/app

COPY . /home/app/

WORKDIR /home/app/function/
RUN chmod +x ./gradlew
RUN ./gradlew build
WORKDIR /home/app/

RUN cp -r ./function/build/libs/* ./libs/
RUN chmod +x ./gradlew
RUN ./gradlew build

RUN chown -R app /home/app
USER app

ENV upstream_url="http://127.0.0.1:8081"
ENV mode="http"
ENV fprocess="java -classpath ./libs/com.openfaas.jar:./build/libs/java8-gradle.jar App"

HEALTHCHECK --interval=2s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]
