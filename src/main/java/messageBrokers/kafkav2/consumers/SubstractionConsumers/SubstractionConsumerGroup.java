package messageBrokers.kafkav2.consumers.SubstractionConsumers;


import lombok.ToString;
import messageBrokers.kafkav2.Message;
import messageBrokers.kafkav2.Topic;
import messageBrokers.kafkav2.consumers.ConsumerGroupState;
import messageBrokers.kafkav2.consumers.ConsumerWorker;
import messageBrokers.kafkav2.consumers.IConsumer;
import messageBrokers.kafkav2.consumers.IConsumerGroup;

import java.util.ArrayList;
import java.util.List;


@ToString
public class SubstractionConsumerGroup implements IConsumerGroup {
    private final List<IConsumer> consumers;
    private final String groupID;
    private final Topic topic;
    private final ConsumerGroupState state;



    public SubstractionConsumerGroup(Topic topic, String groupID, ConsumerGroupState state) {
        this.consumers = new ArrayList<>();
        this.groupID = groupID;
        this.topic = topic;
        this.state = state;
    }


    @Override
    public String getConsumerGroupID() {
        return groupID;
    }

    @Override
    public void registerConsumer(IConsumer consumer) {
        consumers.add(consumer);
        new Thread(new ConsumerWorker(state ,consumer)).start();
        System.out.println("Registered new Consumer for Consumer Group "+ getConsumerGroupID());
    }

    private int getConsumerCount() {
        return consumers.size();
    }

    @Override
    public void publish(Message message) {
        if (getConsumerCount() == 0){
            System.out.println("No consumer workers found for this Group, Ignoring the message");
            System.out.printf("GroupID %s, Topic %s \n", groupID, topic);
            return;
        }
        synchronized (state){
            state.notifyAll();
//            System.out.println("Notified Subtraction Consumers");

        }
    }

    @Override
    public void resetOffset(int index) {
        synchronized (state){
            state.setOffset(index);
            state.notifyAll();
        }
    }

    @Override
    public ConsumerGroupState getConsumerGroupState() {
        return state;
    }


}
