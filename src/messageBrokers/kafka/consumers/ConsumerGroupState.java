package messageBrokers.kafka.consumers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import messageBrokers.kafka.message.Message;

import java.util.List;

@AllArgsConstructor
@Getter
public class ConsumerGroupState {
    String groupID;
    List<Message> messageQ;

//    int lastSeenIndex;
}
