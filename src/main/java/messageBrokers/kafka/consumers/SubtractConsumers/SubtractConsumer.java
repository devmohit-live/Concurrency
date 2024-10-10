package messageBrokers.kafka.consumers.SubtractConsumers;

import lombok.SneakyThrows;
import messageBrokers.kafka.message.MathsBinaryOperation;
import messageBrokers.kafka.message.Message;
import messageBrokers.kafka.consumers.IConsumer;

public class SubtractConsumer implements IConsumer {
    @SneakyThrows
    @Override
    public void consume(Message message) {
        MathsBinaryOperation op = (MathsBinaryOperation) message.getPayload();
        System.out.println(Thread.currentThread().getName() +" : Subtraction Started "+ message);
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() +" : Subtraction Worker completed : "+(op.getFirst() - op.getSecond()));
    }
}
