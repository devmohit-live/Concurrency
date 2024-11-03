package messageBrokers.kafkav2.consumers.SubstractionConsumers;

import lombok.SneakyThrows;
import messageBrokers.kafkav2.Message;
import messageBrokers.kafkav2.consumers.IConsumer;

public class SubstractionConsumer implements IConsumer {
    @SneakyThrows

    @Override
    public void consume(Message message) {
        System.out.println(Thread.currentThread().getName() +" : Subtraction Started "+ message);
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() +" : Subtraction Worker completed : "+(message.getFirst() - message.getSecond()));
    }
}
