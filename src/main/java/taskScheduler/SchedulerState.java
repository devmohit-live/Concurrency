package taskScheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.PriorityQueue;


@AllArgsConstructor
@Getter
public class SchedulerState {
    PriorityQueue<Task> tasks;
}
