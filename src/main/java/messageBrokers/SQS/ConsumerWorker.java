package messageBrokers.SQS;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class ConsumerWorker implements Runnable {
    final SQSState state;
    final IConsumer consumer;

    @SneakyThrows
    @Override
    public void run() {
        while(true) {
            final Message msg;
            synchronized (state) {
                while (state.messageQueue.isEmpty()) {
                    state.wait();
                }
                msg = state.messageQueue.remove(0);
            }
            consumer.consume(msg);
        }
    }
}

