package messageBrokers.SQS;

import lombok.SneakyThrows;


public class AddConsumer implements IConsumer{
    @SneakyThrows
    @Override
    public void consume(Message message) {
        System.out.println(Thread.currentThread().getName() +" : Addition Started "+ message);
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() +" : Addition Worker completed : "+(message.getFirst() + message.getSecond()));
    }
}
