package taskScheduler;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Task {
    Payload payload;
    Long fixedTime;
    TaskType taskType;
    Long recurringTimeInterval;

}
