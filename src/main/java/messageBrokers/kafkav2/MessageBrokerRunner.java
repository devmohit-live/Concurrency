package messageBrokers.kafkav2;

import lombok.SneakyThrows;
import messageBrokers.kafkav2.consumers.AddConsumers.AddConsumer;
import messageBrokers.kafkav2.consumers.AddConsumers.AddConsumerGroup;
import messageBrokers.kafkav2.consumers.ConsumerGroupState;
import messageBrokers.kafkav2.consumers.IConsumerGroup;
import messageBrokers.kafkav2.consumers.MultiplicationConsumers.MultiplicationConsumer;
import messageBrokers.kafkav2.consumers.SubstractionConsumers.SubstractionConsumer;
import messageBrokers.kafkav2.consumers.SubstractionConsumers.SubstractionConsumerGroup;


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
        IConsumerGroup add = new AddConsumerGroup(topic1,"AddConsumerGroup", new ConsumerGroupState(0, topic1.getQueue()));
        IConsumerGroup multiply = new messageBrokers.kafkav2v2.consumers.MultiplicationConsumers.MultiplicationConsumerGroup(topic1,"MultiplicationConsumerGroup", new ConsumerGroupState(0,topic1.getQueue()));
        IConsumerGroup minus = new SubstractionConsumerGroup(topic1,"SubtractConsumerGroup", new ConsumerGroupState(0,topic1.getQueue()));
//        IConsumerGroup add2 = new AddConsumerGroup(topic2,"AddConsumerGroup2", new ConsumerGroupState(0));


        System.out.println("Registering Consumer Groups");
        // Register Consumer Groups to topic
        broker.registerConsumerGroup(topic1.getTopicID(),add);
        broker.registerConsumerGroup(topic1.getTopicID(),minus);
        broker.registerConsumerGroup(topic1.getTopicID(),multiply);
//        broker.registerConsumerGroup(topic2.getTopicName(),add2);
        System.out.println("Consumer Groups Registration Done");



        System.out.println("Adding Consumers to groups: creation of workers");

        // consumers
        add.registerConsumer(new AddConsumer());
        add.registerConsumer(new AddConsumer());

        minus.registerConsumer(new SubstractionConsumer());

        multiply.registerConsumer(new MultiplicationConsumer());
        multiply.registerConsumer(new MultiplicationConsumer());
//        multiply.registerConsumer(new MultiplicationConsumer());
        System.out.println("Consumer Addition done!");


        System.out.println("Publishing Messages");
        // messages  : Topic 1
        broker.pushlishMessage("Topic1",new Message(1,2,"Topic1"));
        broker.pushlishMessage("Topic1",new Message(3,4,"Topic1"));
        broker.pushlishMessage("Topic1",new Message(5,6,"Topic1"));
        broker.pushlishMessage("Topic1",new Message(7,8,"Topic1"));
        Thread.sleep(3000);
        System.out.println("Message Publishing done!");


//        broker.resetOffsetOfAConsumerGroup(topic1.getTopicID(),add.getConsumerGroupID(),0);
//        broker.resetOffsetOfAConsumerGroup(topic1.getTopicID(),multiply.getConsumerGroupID(),1);


//        // Messages: Topic 2 : should not be picked up as no groups
////        broker.pushlishMessage("Topic2",new MathsOperation<MathsBinaryOperation>("Topic2",new MathsBinaryOperation(8,9)));
////        broker.pushlishMessage("Topic2",new MathsOperation<MathsBinaryOperation>("Topic2",new MathsBinaryOperation(9,10)));
//
//
////        // Wrong Topics
////        broker.pushlishMessage("Topic2",new MathsOperation<MathsBinaryOperation>("Topic1",new MathsBinaryOperation(1,2)));
////        broker.pushlishMessage("Topic2",new MathsOperation<MathsBinaryOperation>("Topic1",new MathsBinaryOperation(3,4)));

    }
}
