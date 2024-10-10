package diningPhilosopher;

import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Fork {
    private Integer index;
    private boolean isFree;
    private Lock lock;


    public Fork(Integer index) {
        this.index = index;
        this.isFree = true;
        this.lock = new ReentrantLock();
    }

    public boolean isAvailable(){
        return isFree;
    }

    public void useFork(){
        isFree = false;
    }

    public void releaseFork(){
        isFree = true;
    }
}
