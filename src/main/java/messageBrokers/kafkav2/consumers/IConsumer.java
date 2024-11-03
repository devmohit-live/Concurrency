package messageBrokers.kafkav2.consumers;

import messageBrokers.kafkav2.Message;

public interface IConsumer {
    public void consume(Message message);
}
