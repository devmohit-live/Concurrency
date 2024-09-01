package messageBrokers.SQS;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class SQS {
    final List<IConsumer>consumers;
    final SQSState state;

    public SQS(SQSState state) {
        this.consumers = new ArrayList<>();
        this.state =state;
    }

    public void registerConsumer(IConsumer consumer){
        consumers.add(consumer);
        //NOTE: Each consumer needs to run in its own thread
        new Thread(new ConsumerWorker(state,consumer)).start();
        System.out.println("Consumer registered : "+consumer);
    }

    public void publishMessage(Message message){
        synchronized (state){
            state.messageQueue.add(message);
            // NOTE: Workers are notified that messageQ is having a new message
            state.notifyAll();
        }
    }

}
