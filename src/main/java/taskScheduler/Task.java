package taskScheduler;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Task {
    Payload payload;
    Long fixedTime;
    TaskType taskType;
    Long recurringTimeInterval;

}
