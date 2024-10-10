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
                    Long remainingTime =  task.getFixedTime() -  System.currentTimeMillis();
                    if(remainingTime > 0) {
                        // wait for remaining time
                        schedulerState.wait(remainingTime);
                    }else{
                        break;
                    }
                }
                schedulerState.tasks.poll(); // polling should be a part of sync
                if(task.getTaskType() == TaskType.RECURRING){
                    schedulerState.tasks.add(
                            new Task(
                                task.getPayload(),
// If req is: run it after every interval after the last run:
//                                System.currentTimeMillis() + task.getRecurringTimeInterval(),
// If req is: run it every 10 sec
                                // To avoid jitter we schedule it after the
                                task.getFixedTime() + task.getRecurringTimeInterval(), // this will be new fixed time
                                task.getTaskType(),
                                task.getRecurringTimeInterval()
                            )
                    );
                    schedulerState.notifyAll(); // to tell the runners that new task have been published
                }
            }
            // consuming shouldn't be part of synchronized
            scheduler.run(task);
        }
    }



}
