package messageBrokers.kafka;

import lombok.SneakyThrows;
import messageBrokers.kafka.consumers.AddConsumers.AddConsumer;
import messageBrokers.kafka.consumers.AddConsumers.AddConsumerGroup;
import messageBrokers.kafka.consumers.ConsumerGroupState;
import messageBrokers.kafka.consumers.IConsumerGroup;
import messageBrokers.kafka.consumers.MultiplicationConsumers.MultiplicationConsumer;
import messageBrokers.kafka.consumers.MultiplicationConsumers.MultiplicationConsumerGroup;
import messageBrokers.kafka.consumers.SubtractConsumers.SubtractConsumer;
import messageBrokers.kafka.consumers.SubtractConsumers.SubtractConsumerGroup;
import messageBrokers.kafka.message.MathsBinaryOperation;
import messageBrokers.kafka.message.MathsOperation;
import messageBrokers.kafka.message.Message;

import java.util.ArrayList;

public class MessageBrokerRunner {
    @SneakyThrows
    public void run(){
        MessageBroker broker = new MessageBroker();
        // topics
        Topic topic1 = new Topic("Topic1");
        Topic topic2 = new Topic("Topic2");
        broker.addTopic(topic1);
        broker.addTopic(topic2);

        // consumer groups
        IConsumerGroup add = new AddConsumerGroup(topic1,"AddConsumerGroup", new ConsumerGroupState(topic1.getTopicName(), new ArrayList<>()));
        IConsumerGroup multiply = new MultiplicationConsumerGroup(topic1,"MultiplicationConsumerGroup", new ConsumerGroupState(topic1.getTopicName(), new ArrayList<>()));
        IConsumerGroup minus = new SubtractConsumerGroup(topic1,"SubtractConsumerGroup", new ConsumerGroupState(topic1.getTopicName(), new ArrayList<>()));
        IConsumerGroup add2 = new AddConsumerGroup(topic2,"AddConsumerGroup2", new ConsumerGroupState(topic1.getTopicName(), new ArrayList<>()));

        // consumers
        add.registerConsumer(new AddConsumer());
        add.registerConsumer(new AddConsumer());

        minus.registerConsumer(new SubtractConsumer());

        multiply.registerConsumer(new MultiplicationConsumer());
        multiply.registerConsumer(new MultiplicationConsumer());
//        multiply.registerConsumer(new MultiplicationConsumer());


        // Register Consumer Groups to topic
        broker.registerConsumerGroup(topic1.getTopicName(),add);
        broker.registerConsumerGroup(topic1.getTopicName(),minus);
        broker.registerConsumerGroup(topic1.getTopicName(),multiply);
//        broker.registerConsumerGroup(topic2.getTopicName(),add2);


        // messages  : Topic 1
        broker.pushlishMessage("Topic1",new MathsOperation<MathsBinaryOperation>("Topic1",new MathsBinaryOperation(1,2)));
        broker.pushlishMessage("Topic1",new MathsOperation<MathsBinaryOperation>("Topic1",new MathsBinaryOperation(3,4)));
        broker.pushlishMessage("Topic1",new MathsOperation<MathsBinaryOperation>("Topic1",new MathsBinaryOperation(5,6)));
        broker.pushlishMessage("Topic1",new MathsOperation<MathsBinaryOperation>("Topic1",new MathsBinaryOperation(7,8)));
        Thread.sleep(3000);

        broker.resetOffsetOfAConsumerGroup(topic1.getTopicName(),add.getConsumerGroupID(),0);
        broker.resetOffsetOfAConsumerGroup(topic1.getTopicName(),multiply.getConsumerGroupID(),1);


        // Messages: Topic 2 : should not be picked up as no groups
//        broker.pushlishMessage("Topic2",new MathsOperation<MathsBinaryOperation>("Topic2",new MathsBinaryOperation(8,9)));
//        broker.pushlishMessage("Topic2",new MathsOperation<MathsBinaryOperation>("Topic2",new MathsBinaryOperation(9,10)));


//        // Wrong Topics
//        broker.pushlishMessage("Topic2",new MathsOperation<MathsBinaryOperation>("Topic1",new MathsBinaryOperation(1,2)));
//        broker.pushlishMessage("Topic2",new MathsOperation<MathsBinaryOperation>("Topic1",new MathsBinaryOperation(3,4)));

    }
}
