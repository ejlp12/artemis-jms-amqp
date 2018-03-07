Sample code of simple AMQP client consist of Producer who put message on a queue and Consumer who get message from queue.

It is using Qpid JMS (AMQP 1.0) and tested to work with AMQ 7.1 (ActiveMQ Artemis)

- To build: `mvn clean install`
- To run the producer: `mvn exec:java@producer`
- To run the consumer: `mvn exec:java@consumer`


