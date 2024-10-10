package messageBrokers.kafka.consumers;

import messageBrokers.kafka.message.Message;

public interface IConsumerGroup {
    String getConsumerGroupID();
    void registerConsumer(IConsumer consumer);
    void publish(Message message);
    void resetOffset(int index);
}
