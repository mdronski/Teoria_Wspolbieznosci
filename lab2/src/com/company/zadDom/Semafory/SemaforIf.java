package com.company.zadDom.Semafory;

public class SemaforIf extends Semafor {


    @Override
    public synchronized void acquire() {
        _czeka = _czeka + 1;

        if (!_stan) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        _stan = false;
        _czeka = _czeka - 1;

    }

}
