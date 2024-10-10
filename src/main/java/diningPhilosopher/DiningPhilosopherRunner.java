package diningPhilosopher;

import java.util.List;

public class DiningPhilosopherRunner {
    public void run(){
        DiningPhilosopher diningPhilosopher = new DiningPhilosopher();


        diningPhilosopher.addForks(new Fork(0));
        diningPhilosopher.addForks(new Fork(1));
        diningPhilosopher.addForks(new Fork(2));
        diningPhilosopher.addForks(new Fork(3));
        diningPhilosopher.addForks(new Fork(4));

        List<Fork> forks = diningPhilosopher.getForks();

        diningPhilosopher.addPhilosopher(new Philosopher(0, forks.get(0), forks.get(1)));
        diningPhilosopher.addPhilosopher(new Philosopher(1, forks.get(1), forks.get(2)));
        diningPhilosopher.addPhilosopher(new Philosopher(2, forks.get(2), forks.get(3)));
        diningPhilosopher.addPhilosopher(new Philosopher(3, forks.get(3), forks.get(4)));
        diningPhilosopher.addPhilosopher(new Philosopher(4, forks.get(4), forks.get(0)));

        for(Philosopher p: diningPhilosopher.getPhilosophers()){
            new Thread(p).start();
        }

    }
}
