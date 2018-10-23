package com.company.zadDom.Philosophers;

import java.util.concurrent.Semaphore;

public class Philosopher {
    static private int cnt = 0;
    private Fork leftFork;
    private Fork rightFork;
    private boolean hasLeft = true;
    private boolean hasRight = false;
    private String name;

    public Philosopher(Fork leftFork, Fork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = "Philosopher " + cnt;
        cnt++;
    }

    public void pickUpLeftFork() {
        try {
            this.leftFork.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pickUpRightFork() {
        try {
            this.rightFork.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void putDownLeftFork() {
        this.leftFork.release();
    }

    public void putDownRightFork() {
        this.rightFork.release();
    }

    public void eat() {
        System.out.println(name + " is eating");
        leftFork.setClean(false);
        leftFork.setClean(false);
    }

//    For Waiter


    public Fork getLeftFork() {
        return leftFork;
    }

    public Fork getRightFork() {
        return rightFork;
    }


//    For Resources hierarchy

    public void pickUpLowerFork() {
        if (leftFork.getNumber() < rightFork.getNumber()) {
            try {
                this.leftFork.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.rightFork.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pickUpHigherFork() {
        if (leftFork.getNumber() > rightFork.getNumber()) {
            try {
                this.leftFork.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.rightFork.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void putDownLowerFork() {
        if (leftFork.getNumber() < rightFork.getNumber()) {
            this.leftFork.release();
        } else {
            this.rightFork.release();
        }
    }

    public void putDownHigherFork() {
        if (leftFork.getNumber() > rightFork.getNumber()) {
            this.leftFork.release();
        } else {
            this.rightFork.release();
        }
    }

//    For Chandy Misra


    public boolean isHasLeft() {
        return hasLeft;
    }

    public void setHasLeft(boolean hasLeft) {
        this.hasLeft = hasLeft;
    }

    public boolean isHasRight() {
        return hasRight;
    }

    public void setHasRight(boolean hasRight) {
        this.hasRight = hasRight;
    }
}
