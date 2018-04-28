# Microservice Sandbox 

This Sandbox use a simplified version of an Insurance Claim System.

## Architecture Diagram
Those diagrams are freely inspired from the C4 model from Simons.

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
* Use Hibernate Search in Partner to find partner by birthDate or forname
