package com.company.zadDom.Semafory;

public class Semafor_IFvsWhile {
    Wyscig w1 = new Wyscig(new Semafor(), 100);
    Wyscig w2 = new Wyscig(new SemaforIf(), 1);

    public void start(){
        System.out.println("Semaphore with while loop: ");
        w1.start();
        System.out.println();
        System.out.println("Semaphore with if condition: ");
        w2.start();
    }
}
