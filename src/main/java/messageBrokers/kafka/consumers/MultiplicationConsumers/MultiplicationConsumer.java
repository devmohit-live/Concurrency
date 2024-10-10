package messageBrokers.kafka.consumers.MultiplicationConsumers;

import lombok.SneakyThrows;
import messageBrokers.kafka.consumers.IConsumer;
import messageBrokers.kafka.message.MathsBinaryOperation;
import messageBrokers.kafka.message.Message;

public class MultiplicationConsumer implements IConsumer {
    @SneakyThrows
    @Override
    public void consume(Message message) {
        MathsBinaryOperation op = (MathsBinaryOperation) message.getPayload();
        System.out.println(Thread.currentThread().getName() +" : Multiplication Started "+ message);
        Thread.sleep(6000);
        System.out.println(Thread.currentThread().getName() +" : Multiplication Worker completed : "+(op.getFirst() * op.getSecond()));
    }
}
