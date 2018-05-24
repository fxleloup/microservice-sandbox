[![Build Status](https://travis-ci.org/McKratt/microservice-sandbox.svg?branch=master)](https://travis-ci.org/McKratt/microservice-sandbox)
[![Quality Status](https://sonarcloud.io/api/project_badges/measure?project=net.bakaar%3Amicroservice-sandbox&metric=alert_status)](https://sonarcloud.io/api/project_badges/measure?project=net.bakaar%3Amicroservice-sandbox&metric=alert_status)
[![Code coverage](https://sonarcloud.io/api/project_badges/measure?project=net.bakaar%3Amicroservice-sandbox&metric=coverage)](https://sonarcloud.io/api/project_badges/measure?project=net.bakaar%3Amicroservice-sandbox&metric=coverage)
# Microservice Sandbox 

This Sandbox use a simplified version of an Insurance Claim System.

## Architecture Diagram
Those diagrams are freely inspired from the C4 model from Simon Brown.

### Systems

![Systems Diagram](./doc/images/systems.png)

### Components

![Components Diagram](./doc/images/components.png)

## Use Cases

- Create a Partner in the dedicated system
- Create a Case linked to a Partner, the Case should become a Business Number
- When a Case is created, a Task should appear to remind me to treat the Case
- Change the Business ID of a Partner (the change should cascade to the Claim domain)
- Interrogate the Claim system and show a Case with all the information of the related partner
- Once the Case closed, the related Task should closed too

### NFRs
- Every call should be tracked with a CorrelationID
- Every sent event should resendable
- All system should be idempotent (multiple messages delivery)

## Techological show cases

* Synchronous call with gentle degradation (cache)
* Long business transaction through multiple microservices enduring failure (Sagas)
* Synchronous call with retry
* Communication with messages (Choreography/Broker)
* Communication with messages (Polling)

## TODO's

- [x] Make a todo list ;-)
- [x] Implement VO's for insured and PNummer
- [x] Add TravisCI configuration
- [x] Add SonarQube analysis to the build
- [x] Add Spring-Boot Application to Case to run it and test via rest interface
- [ ] Create docker-compose file to run Case and its DB inside containers
- [ ] Implement Business Number provider
- [ ] Make Business Service running in a container
- [ ] Create Partner service
- [ ] Make Partner Service running in a container
- [ ] Add partner info (Name, Forename, BirthDate)
- [ ] Create the claim link service
- [ ] Create polling service
- [ ] Create a base module (commons?) gathering all the interfaces and base classes 
- [ ] Add call context for all rest calls
- [ ] Add Call context for all logging
- [ ] Add a message broker (RabbitMQ)
- [ ] Put in place the calls tracking system (Jhipster console)
- [ ] Finish messaging implementation (including Call Context)
- [ ] Create the task service (link with messages)
- [ ] Make the partner number change cascade to Case
- [ ] Make the documentation with Structurizr

## Things to explore
* Service discovery with Spring Cloud Eureka
* Call tracking with :
  * Zipkin
  * Jhipster console
* Security with Spring Security
  * JWT
* Distributed Configuration
  * Spring-Cloud-Config
* Use Kubernetes to deploy all the components
  * Use Secret in Kubernetes
  * Use Secret in Spring-Cloud-Config
  * Use Helm ?
* Use Flyway and Liquibase to migrate database
* Use different types of database
* Migrate to Kotlin
* Migrate to Spring-Boot 2
  * Use Async Webflux
* Testing
  * Pact
  * HoverFly or Wiremock
* Monitor
  * Metrics
  * JHispter Console
* Deployment
  * Use DockerHub to deploy Container
  * Use Minikub in local to Deploy
  * Jenkins pipeline as Code
* Use Hibernate Search in Partner to find partner by name or forname
