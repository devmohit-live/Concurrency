package messageBrokers.kafkav2.consumers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import messageBrokers.kafkav2.Message;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ConsumerGroupState {
    Integer offset;
    List<Message> q;

    public void resetIndex(int index){
        if(isValidIndex(index)){
            throw new IndexOutOfBoundsException("Index out of bounds: "+index);
        }
        this.offset = index;
        System.out.printf("------ Resetting index for the consumerGroup to %d ------- \n", index);
    }

    private boolean isValidIndex(int index) {
        return index < 0 || index > offset || index >= q.size();
    }

    public void restartIndex(){
        resetIndex(0);
        System.out.println("Restart from beginning");
    }

    public void incrementIndex(){
        this.offset++;
    }
}
