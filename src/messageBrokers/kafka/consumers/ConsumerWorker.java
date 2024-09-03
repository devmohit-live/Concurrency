package messageBrokers.kafka.consumers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import messageBrokers.kafka.message.Message;


@AllArgsConstructor
public class ConsumerWorker implements Runnable {
    ConsumerGroupState state;
    IConsumer consumer;
    @SneakyThrows
    @Override
    public void run() {
        while (true){
            final Message msg;
            synchronized (state){
                while(state.getMessageQ().isEmpty() || state.getOffset() >= state.getMessageQ().size()){
                    state.wait();
                }
//                msg = state.getMessageQ().remove(0);
                msg = state.getMessageQ().get(state.getOffset());
                state.incrementIndex();
            }

            consumer.consume(msg);
        }
    }
}
