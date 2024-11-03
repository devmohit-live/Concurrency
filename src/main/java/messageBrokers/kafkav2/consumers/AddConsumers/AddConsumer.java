package messageBrokers.kafkav2.consumers.AddConsumers;

import lombok.SneakyThrows;
import messageBrokers.kafkav2.consumers.IConsumer;
import messageBrokers.kafkav2.Message;

public class AddConsumer implements IConsumer {
    @SneakyThrows
    @Override
    public void consume(Message message) {
        System.out.println(Thread.currentThread().getName() +" : Addition Started "+ message);
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() +" : Addition Worker completed : "+(message.getFirst() + message.getSecond()));
    }
}
