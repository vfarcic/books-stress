# Version: 0.1
FROM ubuntu:14.04
MAINTAINER Viktor Farcic "viktor@farcic.com"

# Packages
RUN apt-get update && \
    apt-get -y install --no-install-recommends openjdk-7-jdk && \
    apt-get clean

RUN mkdir /stress
ENV DOMAIN http://172.17.42.1
ENV USERS 100
ENV USERS_OVER_SECONDS 100
ENV MAX_RESPONSE_TIME 1000
COPY bin /stress/bin
COPY conf /stress/conf
COPY lib /stress/lib
COPY user-files /stress/user-files
CMD ["/stress/bin/gatling.sh"]