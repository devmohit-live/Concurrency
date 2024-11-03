package messageBrokers.kafkav2.consumers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import messageBrokers.kafkav2.Message;

import java.util.List;

@AllArgsConstructor
public class ConsumerWorker implements Runnable {
    ConsumerGroupState state;
    IConsumer consumer;

    @SneakyThrows
    @Override
    public void run() {
        while (true){

            final Message message;
            synchronized (state) {
                while (state.getQ().isEmpty() || state.getQ().size() <= state.getOffset()) {
//                    System.out.println("Waiting for message...");
                    state.wait();
                }
                message = state.getQ().get(state.getOffset());
//                System.out.println("Message received: " +message);
                state.incrementIndex();
                state.notifyAll();
            }

            consumer.consume(message);

        }

    }
}
