# Hystrix - Resilience4j - Microprofile Fault Tolerance

This repository is a test around implementation of resilience and (for the moment) around Circuit breaker

I use 3 libraries, and I want to see implementation, configuration, complexity and result.

To help me (and for my curiosity ^_^ of course), I use microprofile starter to initialise all this project

## Context

This is a test around this 3 libraries

- [Hystrix](https://github.com/Netflix/Hystrix) (_Deprecated : use Resilience4j Instead..._)
- [Resilience4j](https://resilience4j.readme.io/)
- [Microprofile Fault Tolerance](https://download.eclipse.org/microprofile/microprofile-fault-tolerance-2.1/microprofile-fault-tolerance-spec.html)

I only use, for this test, the Circuit breaker.

## Microprofile Starter

To initialise the project, i use [Microprofile starter](https://start.microprofile.io/) with this configuration

- MP 3.3
- JDK 11
- Wildfly

### About Wildfly Jar

Version **1.0.0.Alpha4 (include in MP Starter)

[more information about maven plugin](https://docs.wildfly.org/21/Bootable_Guide.html)

## Project

- [Hystrix](hystrix)
- [Resilience4j](resilience4j)
- [MP Fault Tolerance](mp-faulttolerance)

### You need

- JDK 11
- Maven 3.6.5

### Run it

Start Maven commande

```shell
mvn clean package wildfly-jar:run
```


_To be continued..._


