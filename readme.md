# Hystrix - Resilience4j - Microprofile Fault Tolerance

This repository contains a test around implementation of resilience and (for the moment) around Circuit breaker.

I use 3 libraries, and I want to see implementation, configuration, complexity and result.

To help me (and for my curiosity of course ^_^), I use **microprofile starter** to initialise all this project.

## Context

This is a test around this 3 libraries

- [Hystrix](https://github.com/Netflix/Hystrix) (_Deprecated : use Resilience4j Instead..._)
- [Resilience4j](https://resilience4j.readme.io/)
- [Microprofile Fault Tolerance](https://download.eclipse.org/microprofile/microprofile-fault-tolerance-2.1/microprofile-fault-tolerance-spec.html)

I only use, for this test, the "Circuit Breaker".

## Microprofile Starter

To initialise the project, i use [Microprofile starter](https://start.microprofile.io/) with this configuration

- MP 3.3
- JDK 11
- Wildfly

### About Wildfly Jar

Version **1.0.0.Alpha4** (include in MP Starter)

[more information about maven plugin](https://docs.wildfly.org/21/Bootable_Guide.html)

## Projects

Each library is in each folder :

- [Hystrix](hystrix)
- [Resilience4j](resilience4j)
- [MP Fault Tolerance](mp-faulttolerance)

### You need

- JDK 11
- Maven < 3.6.X

### Run it

Start Maven command

```shell
mvn clean package wildfly-jar:run
```

### API

Every project run on http://localhost:8080

#### Project path 

- hystrix : http://localhost:8080/hystrix
- resilience4j : http://localhost:8080/r4j
- Microprofile Fault Tolerance : http://localhost:8080/mp-ft 

#### Endpoints

Every project expose 2 endpoints :

- _/github_ : this endpoint access to github (https://api.github.com/repos/jufab/opentelemetry-angular-interceptor/releases) and have Circuit Breaker configuration but depend on github availability's
- _/github/exception_ : this endpoint generates a BadRequestException to force circuit breaker reaction  

## Circuit Breaker configuration

Every project has the same configuration : 

- Request Volume Threshold : 4
- Error Threshold Percentage : 50% 
- Sleep Window (Duration) : 5 seconds

So, If there are 2 errors on 4 requests (50%), circuit breaker is open during 5 seconds.

_At first try, there is 4 requests before trigger the circuit breaker..._

## Results

### Hystrix

In exception endpoint

- _Circuit breaker close_ : expose an HystrixException with a BadRequestException cause
- _Circuit breaker open_ : expose an HystrixException with a RuntimeException cause

### Resilience4j

In exception endpoint

- _Circuit breaker close_ : expose a BadRequestException
- _Circuit breaker open_ : expose a CallNotPermittedException

### Microprofile fault tolerance

In exception endpoint

- _Circuit breaker close_ : expose a BadRequestException
- _Circuit breaker open_ : expose a CircuitBreakerOpenException


_Next step : add tests _


