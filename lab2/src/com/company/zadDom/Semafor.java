package com.company.zadDom;

public class Semafor {

    private boolean _stan = true;
    private int _czeka = 0;

    public Semafor() {
    }

    public synchronized void acqurie() {
        _czeka = _czeka + 1;

        while(!_stan){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        _czeka = _czeka - 1;
        _stan = false;

    }

    public synchronized void release() {
        _stan = true;

        if (_czeka > 0)
            this.notifyAll();
    }

}
