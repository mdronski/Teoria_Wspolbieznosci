package com.company.zadDom.Semafory;

public class SemaforLicznikowy implements ISemafor {
    private Semafor sectionLock = new Semafor();
    private Semafor resourceLock = new Semafor();
    private int cnt = 1;


    public SemaforLicznikowy() { }

    public SemaforLicznikowy(int initialValue){
        this.cnt = initialValue;
    }

    public void acquire(){
        resourceLock.acquire();
        cnt = cnt - 1;

        if (cnt < 1){
            resourceLock.release();
            sectionLock.acquire();
        } else {
            resourceLock.release();
        }

    }

    public void release(){
        resourceLock.acquire();
        cnt = cnt + 1;

        if (cnt < 2){
            sectionLock.release();
        }

        resourceLock.release();
    }
}
