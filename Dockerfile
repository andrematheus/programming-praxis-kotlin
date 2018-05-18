FROM andreroquem/kotlin-build

MAINTAINER André Roque Matheus <amatheus@mac.com>

RUN mkdir /app

COPY . /app

RUN cd /app; mvn -q test