package messageBrokers.SQS;

import lombok.SneakyThrows;


public class SubstractionConsumer implements IConsumer{
    private boolean isFree = true;
    @SneakyThrows
    @Override
    public void consume(Message message) {
        isFree = false;
        System.out.println(Thread.currentThread().getName() +" : Addition Started "+ message);
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() +" : Addition Worker completed : "+(message.getFirst() + message.getSecond()));
        isFree = true;
    }
}
