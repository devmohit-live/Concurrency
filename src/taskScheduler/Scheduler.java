package taskScheduler;

import lombok.SneakyThrows;

public class Scheduler implements IScheduler {

    @SneakyThrows
    @Override
    public void run(Task task) {
        Thread.sleep(5000);
        MathPayload payload = (MathPayload)task.getPayload();

        System.out.printf("Thread %s completed the task with output %d, ", Thread.currentThread().getName() , (payload.getA() + payload.getB()));
        System.out.printf("Time for task was %d \n", System.currentTimeMillis() + task.getTime());

    }
}
