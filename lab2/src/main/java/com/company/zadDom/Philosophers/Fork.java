package com.company.zadDom.Philosophers;

import java.util.concurrent.Semaphore;

public class Fork extends Semaphore {

    private int number;
    private boolean isClean = false;

    public Fork(int permits, int number) {
        super(permits);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }
}
