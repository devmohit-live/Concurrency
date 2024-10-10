## Implement a multithreaded queue like SQS where consumers can consume messages concurrently.

Design and implement an in memory, multithreaded queue system similar to Amazon SQS that allows asynchronous message processing.
Queue should support multiple producers and consumers working concurrently.

EX: Message published contains 2 numbers
Consumer : AddWorker : adds and  produces result 

SQS -> n consumers(of same type) only one of them will consume the message at a time(1 msg is only consumed by 1 consumer)



**Points : About SQS**
- SQS is basically a kafka with a single pipe.
- SNS is pub sub(fan-out)
- SQS uses polling mechanism(polls q for new msg)

**Out of Scope**
- We are not building a FIFO Q, (but there is only once delivery)
- We are not taking into consideration that if a consumer consumes the msg and failed -> in this case the message will be lost


