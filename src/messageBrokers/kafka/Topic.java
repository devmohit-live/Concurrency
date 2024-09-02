package messageBrokers.kafka;

import lombok.Getter;
import messageBrokers.kafka.consumers.IConsumerGroup;
import messageBrokers.kafka.message.Message;

import java.util.ArrayList;
import java.util.List;



@Getter
public class Topic {
    private final String topicName;
    private final List<Message> messagesQ;
    private final List<IConsumerGroup> consumerGroups;

    Topic(String topicName){
        this.topicName = topicName;
        this.messagesQ = new ArrayList<Message>();
        this.consumerGroups = new ArrayList<>();
    }

    @Override
    public String toString(){
        return topicName;
    }

    public void publish(Message message){

        if(!validateMessagePublishing(message)){
            return;
        }
        consumerGroups.forEach(group -> {
            group.publish(message);
//            System.out.println("Published message for Groups " + group);
        });
    }

    public void registerConsumerGroup(IConsumerGroup consumerGroup){
        consumerGroups.add(consumerGroup);
        System.out.println("Registered consumer group : "+ consumerGroup+" for topic: "+topicName);
    }

    private boolean validateMessagePublishing(Message message){
        if(!message.getTopic().equals(this.topicName)){
            // Ignore this message
            System.out.println("Ignoring the message, as the topic is not the same as the topicName.");
            System.out.printf("Message is intended for  Topic : %s Instead of %s \n", message.getTopic(), this.topicName);
            return false;
        }
        if(consumerGroups.isEmpty()){
            System.out.println("No consumerGroups found for topic : " + topicName);
            return false;
        }
        return true;
    }

}
