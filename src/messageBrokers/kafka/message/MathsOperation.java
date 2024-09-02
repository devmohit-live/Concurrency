package messageBrokers.kafka.message;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class MathsOperation<MathsBinaryOperation> implements Message{
    final String topic;
    final MathsBinaryOperation payload;

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public MathsBinaryOperation getPayload() {
        return payload;
    }
}
