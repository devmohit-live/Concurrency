package messageBrokers.kafka.consumers.AddConsumers;


import lombok.ToString;
import messageBrokers.kafka.Topic;
import messageBrokers.kafka.consumers.ConsumerGroupState;
import messageBrokers.kafka.consumers.ConsumerWorker;
import messageBrokers.kafka.consumers.IConsumer;
import messageBrokers.kafka.consumers.IConsumerGroup;
import messageBrokers.kafka.message.Message;

import java.util.ArrayList;
import java.util.List;

@ToString
public class AddConsumerGroup implements IConsumerGroup {
    private List<IConsumer> consumers;
    private final String groupID;
    private final Topic topic;
    private final ConsumerGroupState state;

    public AddConsumerGroup(Topic topic,  String groupID, ConsumerGroupState state) {
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
//        System.out.println("Registered new Consumer for Consumer Group "+ getConsumerGroupID());
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
            state.getMessageQ().add(message);
            state.notifyAll();
        }
    }

    @Override
    public void resetOffset(int index) {
        synchronized (state){
            state.resetIndex(index);
            state.notifyAll();
        }
    }

}
