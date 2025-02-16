FROM alpine

WORKDIR /root/demo
COPY Abc.java /root/demo

RUN apk add openjdk8

ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:$JAVA_HOME/bin

RUN javac Abc.java

# This is a comment: Alternate to run java command is CMD["java","ABC"]
ENTRYPOINT java Abc