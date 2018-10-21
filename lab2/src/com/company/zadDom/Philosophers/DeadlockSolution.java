package com.company.zadDom.Philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class DeadlockSolution implements PhilosophersProblem {
    public List<Philosopher> philosophers = new ArrayList<>();
    public List<Fork> forks = new ArrayList<>();

    public DeadlockSolution() {
        for (int i = 0; i < 5; i++) {
            forks.add(new Fork(1, i));
        }
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                philosophers.add(new Philosopher(forks.get(4), forks.get(0)));
            } else {
                philosophers.add(new Philosopher(forks.get(i - 1), forks.get(i)));
            }
        }
    }

    public void startEating() {
        List<Thread> eatingPhilosophers = new ArrayList<>();
        for (Philosopher philosopher : philosophers) {
            eatingPhilosophers.add(new Thread(() -> {
                for (int i=0; i<100; i++){
                    philosopher.pickUpLeftFork();
                    philosopher.pickUpRightFork();
                    philosopher.eat();
                    philosopher.putDownLeftFork();
                    philosopher.putDownRightFork();
                }
            }));
        }

        for (Thread thread : eatingPhilosophers)
            thread.start();

        for (Thread thread : eatingPhilosophers){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



}
