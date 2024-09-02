package messageBrokers.kafka.consumers;

import messageBrokers.kafka.Topic;
import messageBrokers.kafka.message.Message;

import java.util.List;

public interface IConsumerGroup {
    String getConsumerGroupID();
    void registerConsumer(IConsumer consumer);
    public void publish(Message message);
}
