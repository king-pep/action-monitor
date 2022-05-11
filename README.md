# Getting Started

This is an action monitor assessment project for betvictor java developer role.

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
7. Enter auth key and websocket key in the Client page and click on the `Connect` button.
8. Enter the receiver id and message in the Client page and click on the `Send Message` button.

## Unit Testing and Integration Testing strategy
1. Creation of a Test Plan
2. Creation of Test Cases and the Test Data
3. Execution of the test cases, once the code is ready
4. Fixing of the bugs if present and re testing of the code
5. Repetition of the test cycle until the Unit is free from all types of bugs
6. Framework for the test cases: JUnit and Mockito.



