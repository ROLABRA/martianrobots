FROM buildpack-deps:stretch-curl

ENV FILENAME="jdk-8u161-linux-x64.tar.gz" JAVA_HOME="/opt/jdk"

RUN set -ex \
    && mkdir -p ${JAVA_HOME} \
    && cd ${JAVA_HOME} \
    && wget -qO- http://api.vulhub.org/download/jdk/8/${FILENAME} | tar xz --strip-components=1 \
    && update-alternatives --install /usr/bin/java java /opt/jdk/bin/java 100 \
    && update-alternatives --install /usr/bin/javac javac /opt/jdk/bin/javac 100

WORKDIR /
ADD build/libs/martianrobots-0.0.1-SNAPSHOT.jar martianrobots-0.0.1-SNAPSHOT.jar
CMD java -jar martianrobots-0.0.1-SNAPSHOT.jar