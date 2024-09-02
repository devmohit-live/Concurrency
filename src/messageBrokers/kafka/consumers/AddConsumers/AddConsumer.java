package messageBrokers.kafka.consumers.AddConsumers;

import lombok.SneakyThrows;
import messageBrokers.kafka.message.MathsBinaryOperation;
import messageBrokers.kafka.message.Message;
import messageBrokers.kafka.consumers.IConsumer;

public class AddConsumer implements IConsumer {
    @SneakyThrows
    @Override
    public void consume(Message message) {
        MathsBinaryOperation op = (MathsBinaryOperation) message.getPayload();
        System.out.println(Thread.currentThread().getName() +" : Addition Started "+ message);
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() +" : Addition Worker completed : "+(op.getFirst() + op.getSecond()));
    }
}
