import boundedblockingqueue.BBQRunner;
import messageBrokers.SQS.SQSRunner;
import messageBrokers.kafka.MessageBroker;
import messageBrokers.kafka.MessageBrokerRunner;
import sequence.numbers.evenoddzerofibo.EvenOddZeroRunner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//       new HelloRunner().run();
//        new EvenOddRunner().run();
//        new EvenOddZeroRunner().run();
//        new BBQRunner().run();
//        new SQSRunner().run();
        new MessageBrokerRunner().run();
    }
}