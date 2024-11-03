package messageBrokers.kafkav2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import messageBrokers.kafkav2.consumers.ConsumerGroupState;
import messageBrokers.kafkav2.consumers.IConsumerGroup;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Topic {
    String topicID;
    List<Message> queue;
    List<IConsumerGroup> subscribers;

    public Topic(String topicID) {
        this.topicID = topicID;
        this.subscribers = new ArrayList<>();
        this.queue = new ArrayList<>();
    }


    @Override
    public String toString(){
        return topicID;
    }

    public void publish(Message message){
        if(!validateMessagePublishing(message)){
            return;
        }
        // since here the q is common for all the consumer groups and
        // they only differ by the interger offset so adding of msg to the q will happen only once not for each group
//        queue.add(message);

        queue.add(message);
        subscribers.forEach(group -> {
            group.publish(message);
//            System.out.println("Published message for Groups " + group);
        });

    }

    public void registerConsumerGroup(IConsumerGroup consumerGroup){
        subscribers.add(consumerGroup);
        System.out.println("Registered consumer group : "+ consumerGroup+" for topic: "+topicID);
    }

    private boolean validateMessagePublishing(Message message){
        if(!message.getTopic().equals(this.topicID)){
            // Ignore this message
            System.out.println("Ignoring the message, as the topic is not the same as the topicID.");
            System.out.printf("Message is intended for  Topic : %s Instead of %s \n", message.getTopic(), this.topicID);
            return false;
        }
        if(subscribers.isEmpty()){
            System.out.println("No consumerGroups found for topic : " + topicID);
            return false;
        }
        return true;
    }
}
