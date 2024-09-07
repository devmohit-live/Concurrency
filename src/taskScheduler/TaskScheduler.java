package taskScheduler;

import java.util.ArrayList;
import java.util.List;

public class TaskScheduler {
    List<IScheduler>schedulers;
    SchedulerState state;

    public TaskScheduler(SchedulerState state) {
        this.state = state;
        this.schedulers = new ArrayList<>();
    }

    public void scheduleAfterInterval(Payload payload, Long interval) {
        scheduleAtFixedTime(payload, System.currentTimeMillis() +  interval);
    }

    // interval is in seconds
    public void scheduleAtFixedTime(Payload payload, Long time) {
        scheduleTask(payload,time,TaskType.NORMAL,null);
    }


    private void scheduleTask(Payload payload, Long time, TaskType taskType, Long recurringInterval) {
        if(time < System.currentTimeMillis()) {
            throw new IllegalArgumentException("Time must be greater than or equal to the current time");
        }
        synchronized (state) {
            state.getTasks().add(new Task(payload,time, taskType,recurringInterval));
            state.notifyAll();
        }
    }



    public void scheduleRecurringTask(Payload payload, Long startTime , Long interval) {
        scheduleTask(payload, startTime, TaskType.RECURRING, interval);
    }


    public void registerSchedulerWorker(IScheduler scheduler) {
        schedulers.add(scheduler);
        new Thread(new Worker(state ,scheduler)).start();
        System.out.println("Registered scheduler");
    }

}
