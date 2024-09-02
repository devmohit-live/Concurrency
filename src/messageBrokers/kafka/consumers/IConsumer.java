package messageBrokers.kafka.consumers;

import messageBrokers.kafka.message.Message;

public interface IConsumer {
    public void consume(Message message);
}
