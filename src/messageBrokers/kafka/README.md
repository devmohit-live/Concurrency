# Design a message broker like kafka

1. Basic Kafka
2. **Extension:** Offset reset.

### Some points about kafka:
- Multiple Pipes(like multiple sqs)
- A topic can be subscribed my multiple consumer groups
- A group can have multiple consumers(consumer workers)
- A consumer group can subscribe multiple topics


### Example:

AdditionSubscriber x 2 , 
SubtractionSubscriber x 1,
MultiplicationSubscriber x 2,

Subscribed to a topic called "MathsOperation", where a OperationMessage containing 2 numbers will be published one 1 consumerWorker form each group will consume that message.
(only 1 from each group).


**Extension** : In case there was some problem with the consumers/consumer groups, that Message Broker can advise the consumer group to restart from some index or from 0 to work on those messages again.



