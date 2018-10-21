package com.company.zadDom.Semafory;

public class Semafor implements ISemafor {

    public boolean _stan = true;
    public int _czeka = 0;

    public Semafor() {
    }

    public synchronized void acquire() {
        _czeka = _czeka + 1;

        while(!_stan){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        _stan = false;
        _czeka = _czeka - 1;

    }

    public synchronized void release() {
        _stan = true;

        if (_czeka > 0)
            this.notify();
    }

}
