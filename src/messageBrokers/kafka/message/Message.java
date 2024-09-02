package messageBrokers.kafka.message;

public interface Message<T> {
    String getTopic();
    T getPayload();
}
