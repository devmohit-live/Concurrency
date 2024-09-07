package taskScheduler;

import java.util.PriorityQueue;

public class TaskSchedulerRunner {
    public void run(){
        SchedulerState state = new SchedulerState(new PriorityQueue<>((a,b)->Long.compare(a.getFixedTime() , b.getFixedTime())));
        TaskScheduler ts = new TaskScheduler(state);
        Scheduler s1 = new Scheduler();
        Scheduler s2 = new Scheduler();
        Scheduler s3 = new Scheduler();

        // workers/consumers for a group
        ts.registerSchedulerWorker(s1);
        ts.registerSchedulerWorker(s2);
        ts.registerSchedulerWorker(s3);


        // ------- schedule tasks after an interval from now  : normal tasks ------
//        ts.scheduleAfterInterval(new MathPayload(1,2), 3000l);
//        ts.scheduleAfterInterval(new MathPayload(2,3), 3000l);
//        ts.scheduleAfterInterval(new MathPayload(3,4), 4000l);
//        ts.scheduleAfterInterval(new MathPayload(4,5), 8000l);
//        ts.scheduleAfterInterval(new MathPayload(5,6), 6000l);
//        ts.scheduleAfterInterval(new MathPayload(6,7), 5000l);
//        ts.scheduleAfterInterval(new MathPayload(7,8), 1000l);
//        ts.scheduleAfterInterval(new MathPayload(8,9), 2000l);



        // ------- schedule tasks at fixed interval : normal tasks ------
//        ts.scheduleAtFixedTime(new MathPayload(1,2),System.currentTimeMillis() + 5000);
//        ts.scheduleAtFixedTime(new MathPayload(2,3),System.currentTimeMillis() + 3000);
//        ts.scheduleAtFixedTime(new MathPayload(5,6),System.currentTimeMillis() + 1000);


        ts.scheduleRecurringTask(new MathPayload(1,2),System.currentTimeMillis() + 3000,5000l);

    }
}
