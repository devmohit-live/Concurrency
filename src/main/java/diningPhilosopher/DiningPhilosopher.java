package diningPhilosopher;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class DiningPhilosopher {
    List<Philosopher> philosophers;
    List<Fork> forks;
    public DiningPhilosopher() {
        this.philosophers = new ArrayList<>();
        this.forks = new ArrayList<>();
    }

    public void addPhilosopher(Philosopher philosopher) {
        this.philosophers.add(philosopher);
    }

    public void addForks(Fork fork) {
        this.forks.add(fork);
    }

}
