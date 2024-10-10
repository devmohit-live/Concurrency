package messageBrokers.SQS;

public interface IConsumer {
    void consume(Message message);
}
