package boundedblockingqueue;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class BoundedBlockingQueue<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private final int capacity;
    private int size;
    private List<T> q;
    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.q = new ArrayList<T>(capacity);
    }

    BoundedBlockingQueue(){
       this(DEFAULT_CAPACITY);
    }


    @SneakyThrows
    public void enqueue(T t){
      synchronized (this.q){
          while(q.size() == capacity){
              System.out.println(Thread.currentThread().getName() +" Enqueue in waiting state");
              q.wait();
          }
          System.out.println(Thread.currentThread().getName() + " enqueue: woken up");
          q.add(t);
          System.out.println("enqueue: " + t + " Queue is "+ this.q);
          size++;
          q.notifyAll();
      }
    }

    @SneakyThrows
    public T dequeue(){
        synchronized (this.q){
            while(this.q.isEmpty()){
                System.out.println(Thread.currentThread().getName() +" Dequeue in waiting state");
                q.wait();
            }
            System.out.println(Thread.currentThread().getName() +" dequeue: woken up");
            T val =  this.q.remove(0);
            System.out.println("Dequed Val is " + val+ " Queue is "+ this.q);
            q.notifyAll();
            return  val;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}
