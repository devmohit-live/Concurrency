package diningPhilosopher;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@AllArgsConstructor
public class Philosopher implements Runnable {

    Integer index;
    Fork leftFork;
    Fork rightFork;

    @Override
    public void run() {
        while (true){
           if(leftFork.isAvailable()){
               if(leftFork.getLock().tryLock()){
                    try{
                        leftFork.useFork();
                        if(rightFork.isAvailable()){
                            if(rightFork.getLock().tryLock()){
                                try {
                                    rightFork.useFork();
                                    eat();
                                    rightFork.releaseFork();
                                }finally {
                                    rightFork.getLock().unlock();
                                }
                            }
                        }
                        leftFork.releaseFork();
                    }finally {
                        leftFork.getLock().unlock();
                    }
               }
           }else {
               think();
           }
        }
    }

    @SneakyThrows
    public void think(){
        System.out.println("Philosopher "+ index +" thinking...");
        Thread.sleep(5000);
        System.out.println("Philosopher "+ index +" thinking done");

    }

    @SneakyThrows
    public void eat(){
        System.out.println("Philosopher "+ index +" eating ...");
        Thread.sleep(3000);
        System.out.println("Philosopher "+ index +" eating done");
    }
}
