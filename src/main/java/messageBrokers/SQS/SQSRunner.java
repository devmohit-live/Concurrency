package messageBrokers.SQS;

import java.util.ArrayList;

public class SQSRunner {
    public void run(){
        final SQSState state = new SQSState("topic", new ArrayList<>());
        final SQS sqs = new SQS(state);

        sqs.registerConsumer(new AddConsumer());
        sqs.registerConsumer(new AddConsumer());
        sqs.registerConsumer(new AddConsumer());


        sqs.publishMessage(new Message(1,2));
        sqs.publishMessage(new Message(2,3));
        sqs.publishMessage(new Message(3,4));
        sqs.publishMessage(new Message(4,5));
        sqs.publishMessage(new Message(5,6));

    }
}
