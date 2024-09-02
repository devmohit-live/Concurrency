package messageBrokers.kafka;

import messageBrokers.kafka.consumers.IConsumerGroup;
import messageBrokers.kafka.message.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageBroker {
    private List<Topic> topics;
    // create / update topic
    public MessageBroker(){
        this.topics = new ArrayList<Topic>();
    }
    public void addTopic(Topic topic){
        this.topics.add(topic);
    }

    public Topic getTopicByID(String topicID){
        Topic filteredTopic = topics.stream().filter(topic->topic.getTopicName().equals(topicID)).findFirst().orElse(null);
        if(filteredTopic==null){
            throw new IllegalArgumentException("Topic not found");
        }
        return filteredTopic;
    }
    public void registerConsumerGroup(String topicID , IConsumerGroup consumerGroup){
        Topic topic = getTopicByID(topicID);
        topic.registerConsumerGroup(consumerGroup);
    }

    public void pushlishMessage(String topicID , Message message){
        Topic topic = getTopicByID(topicID);
        topic.publish(message);
    }

}
