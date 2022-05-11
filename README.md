# Getting Started

This is an assessment project of how to use Spring WebSocket integrated with Message Broker Active MQ to deal with the scenario where you scale out your Microservice and keep the WebSocket communication working between different Clients connected to your backend service.

## Pre Requirements
- IntelliJ IDE or any IDE of your choice
- Maven
- JDK 11 or higher
- Docker
- Docker Compose

## How to Run

1. Clone the project and open it with IntelliJ or any IDE of your choice.
2. Navigate to the project folder and run the Active MQ Artemis by executing the command `docker-compose -f broker-compose.yml up mq`
3. Navigate to the project folder and run the Sonarqube server by executing the command  `docker-compose -f broker-compose.yml up sonarqube`
4. Run the class `ActionMonitorApplication`
5. Access the Client page at http://localhost:8181
6. On IntelliJ or the IDE of your choice duplicate the Runner for `ActionMonitorApplication` and add the VM parameter `-Dserver.port=8282` to open a new instance in other port.



