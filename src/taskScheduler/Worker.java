package taskScheduler;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class Worker implements Runnable {
    SchedulerState schedulerState;
    IScheduler scheduler;

    @SneakyThrows
    @Override
    public void run() {
        while(true) {
            Task task;
            // Thread will be woken in 2 cases :
            // 1. Waiting time given while wait is passed
            // 2. Received a signal to wake up
            synchronized (schedulerState) {
                while(true){
                    while (schedulerState.getTasks().isEmpty()) {
                        schedulerState.wait();
                    }
                    task = schedulerState.tasks.peek();
                    Long remainingTime =  task.getTime() -  System.currentTimeMillis();
                    if(remainingTime > 0) {
                        // wait for remaining time
                        schedulerState.wait(remainingTime);
                    }else{
                        break;
                    }
                }
                schedulerState.tasks.poll(); // polling should be a part of sync
                if(task.getTaskType() == TaskType.RECURRING){ // can be changed to task type check
                    schedulerState.tasks.add(
                            new Task(
                                task.getPayload(),
                                System.currentTimeMillis() + task.getRecurringTimeInterval(),
//                                task.getTime() + task.getRecurringTimeInterval(),
                                task.getTaskType(),
                                task.getRecurringTimeInterval()
                            )
                    );
                }
            }
            // consuming shouldn't be part of synchronized
            scheduler.run(task);
        }
    }



}
