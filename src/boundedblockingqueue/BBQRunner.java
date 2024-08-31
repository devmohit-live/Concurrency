package boundedblockingqueue;

import lombok.SneakyThrows;

public class BBQRunner {
    public void run() {
        BoundedBlockingQueue bbq = new BoundedBlockingQueue(2);
        new Thread(new Runnable() {
            @SneakyThrows
            public void run() {
//                9 enqueues
                bbq.enqueue(10);
                Thread.sleep(5000);
                bbq.enqueue(20);
                Thread.sleep(3000);
                bbq.enqueue(30);
                Thread.sleep(3000);

                bbq.enqueue(40);
                bbq.enqueue(50);
                bbq.enqueue(50);
                Thread.sleep(6000);
                bbq.enqueue(60);
                bbq.enqueue(70);
                bbq.enqueue(80);

            }
        }).start();


        new Thread(new Runnable() {
            @SneakyThrows
            public void run() {
                // 8 dequeues
                bbq.dequeue();
                bbq.dequeue();
                bbq.dequeue();
                bbq.dequeue();
               Thread.sleep(5000);
               bbq.dequeue();
               Thread.sleep(7000);
                bbq.dequeue();
               Thread.sleep(5000);
                bbq.dequeue();
                bbq.dequeue();

            }
        }).start();


        // net elements left in q = 1
    }
}
