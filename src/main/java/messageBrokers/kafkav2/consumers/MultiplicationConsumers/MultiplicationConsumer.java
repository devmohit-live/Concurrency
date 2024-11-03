package messageBrokers.kafkav2.consumers.MultiplicationConsumers;

import lombok.SneakyThrows;
import messageBrokers.kafkav2.consumers.IConsumer;
import messageBrokers.kafkav2.Message;

public class MultiplicationConsumer implements IConsumer {
    @SneakyThrows

    @Override
    public void consume(Message message) {
        System.out.println(Thread.currentThread().getName() +" : Multiplication Started "+ message);
        Thread.sleep(6000);
        System.out.println(Thread.currentThread().getName() +" : Multiplication Worker completed : "+(message.getFirst() * message.getSecond()));
    }
}
