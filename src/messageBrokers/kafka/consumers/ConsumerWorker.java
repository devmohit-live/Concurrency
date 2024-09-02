package messageBrokers.kafka.consumers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import messageBrokers.kafka.message.Message;
import messageBrokers.kafka.Topic;


@AllArgsConstructor
public class ConsumerWorker implements Runnable {
    ConsumerGroupState state;
    IConsumer consumer;
    @SneakyThrows
    @Override
    public void run() {
        while (true){
            final Message msg;
            synchronized (state.getMessageQ()){
                while(state.getMessageQ().isEmpty()){
                    state.getMessageQ().wait();
                }
                msg = state.getMessageQ().remove(0);
            }

            consumer.consume(msg);
        }
    }
}
