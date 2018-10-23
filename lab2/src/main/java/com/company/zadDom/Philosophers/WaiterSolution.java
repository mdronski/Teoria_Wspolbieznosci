package com.company.zadDom.Philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class WaiterSolution implements PhilosophersProblem {

    public List<Philosopher> philosophers = new ArrayList<>();
    public List<Fork> forks = new ArrayList<>();
    private Semaphore waiter = new Semaphore(1);

    public WaiterSolution() {
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

    @Override
    public void startEating() {

        List<Thread> eatingPhilosophers = new ArrayList<>();
        for (Philosopher philosopher : philosophers) {
            eatingPhilosophers.add(new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    try {
                        waiter.acquire();
                        philosopher.pickUpLeftFork();
                        philosopher.pickUpRightFork();
                        waiter.release();
                        philosopher.eat();
                        philosopher.putDownLeftFork();
                        philosopher.putDownRightFork();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }));
        }

        for (Thread thread : eatingPhilosophers)
            thread.start();

        for (Thread thread : eatingPhilosophers) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}

