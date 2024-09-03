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
    Integer offset;


    public ConsumerGroupState(String groupID, List<Message> messageQ) {
        this.groupID = groupID;
        this.messageQ = messageQ;
        this.offset = 0;
    }

    public void resetIndex(int index){
        if(isValidIndex(index)){
            throw new IndexOutOfBoundsException("Index out of bounds: "+index);
        }
        this.offset = index;
        System.out.printf("------ Resetting index for the consumerGroup %s to %d ------- \n", groupID, index);
    }

    private boolean isValidIndex(int index) {
        return index < 0 || index > offset || index >= messageQ.size();
    }

    public void restartIndex(){
        resetIndex(0);
        System.out.println("Restart from beginning");
    }

    public void incrementIndex(){
        this.offset++;
    }
}
