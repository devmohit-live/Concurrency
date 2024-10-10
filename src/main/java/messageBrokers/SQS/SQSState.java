package messageBrokers.SQS;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SQSState {
    final String topic;
    final List<Message> messageQueue;
}
