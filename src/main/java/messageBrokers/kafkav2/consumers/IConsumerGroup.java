package messageBrokers.kafkav2.consumers;

import messageBrokers.kafkav2.Message;

// Subscriber/ConsumerGroup
public interface IConsumerGroup {
    String getConsumerGroupID();
    // Adds the worker to a consumer group
    void registerConsumer(IConsumer consumer);
    void publish(Message message);
    void resetOffset(int index);
    ConsumerGroupState getConsumerGroupState();
}
