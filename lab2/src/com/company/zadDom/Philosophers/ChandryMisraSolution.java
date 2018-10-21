package com.company.zadDom.Philosophers;

import java.util.ArrayList;
import java.util.List;

public class ChandryMisraSolution implements PhilosophersProblem{
    public List<Philosopher> philosophers = new ArrayList<>();
    public List<Fork> forks = new ArrayList<>();

    public ChandryMisraSolution() {
        for (int i = 0; i < 5; i++) {
            forks.add(new Fork(1, i));
        }
        for (int i = 0; i < 5; i++) {
            if (i == 4) {
                philosophers.add(new Philosopher(forks.get(4), forks.get(0)));
            } else {
                philosophers.add(new Philosopher(forks.get(i), forks.get(i+1)));
            }
        }
    }

    @Override
    public void startEating() {
        List<Thread> eatingPhilosophers = new ArrayList<>();
        for (Philosopher philosopher : philosophers) {
            eatingPhilosophers.add(new Thread(() -> {
                for (int i=0; i<100; i++){
                    while (philosopher.isHasLeft() && philosopher.isHasRight()){
                        if (!philosopher.isHasLeft() && !philosopher.getLeftFork().isClean()){
                            philosopher.pickUpLeftFork();
                            philosopher.setHasLeft(true);
                            philosopher.getLeftFork().setClean(true);
                        }

                        if (!philosopher.isHasRight() && !philosopher.getRightFork().isClean()){
                            philosopher.pickUpRightFork();
                            philosopher.setHasRight(true);
                            philosopher.getRightFork().setClean(true);
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                    philosopher.eat();

                    philosopher.getLeftFork().setClean(false);
                    philosopher.getLeftFork().setClean(false);
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
