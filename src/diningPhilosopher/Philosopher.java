package diningPhilosopher;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@AllArgsConstructor
public class Philosopher implements Runnable {

    Integer index;
    Fork leftFork;
    Fork rightFork;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            think();
            if (leftFork.isAvailable()) {
                if (leftFork.getLock().tryLock()) {
                    try {
                        leftFork.useFork();
                        if (rightFork.isAvailable()) {
                            if (rightFork.getLock().tryLock()) {
//                            if (rightFork.getLock().tryLock(100 , MILLISECONDS)) { //or wait for this much interval do not immediately return if
//                                found a locked resource.
                                try {
                                    rightFork.useFork();
                                    eat();
                                    rightFork.releaseFork();
                                } finally {
                                    rightFork.getLock().unlock();
                                }
                            }
                        }
                        leftFork.releaseFork();
                    } finally {
                        leftFork.getLock().unlock();
                    }
                }
            }
        }
    }

    @SneakyThrows
    public void think() {
        System.out.println("Philosopher " + index + " thinking...");
        Thread.sleep(5000);
        System.out.println("Philosopher " + index + " thinking done");

    }

    @SneakyThrows
    public void eat() {
        System.out.println("Philosopher " + index + " eating ...");
        Thread.sleep(3000);
        System.out.println("Philosopher " + index + " eating done");
    }
}
