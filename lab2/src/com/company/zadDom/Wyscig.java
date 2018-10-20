package com.company.zadDom;

public class Wyscig {
    private Integer cnt = 0;
    private Semafor semafor = new Semafor();
    private int N = 10000;
    private boolean isSynchronised;

    public Wyscig(){
        this(true);
    }

    public Wyscig(boolean isSynchronised){
        this.isSynchronised = isSynchronised;
    }


    public void start(){

        Thread incrementator = new Thread(() -> {
            for (int i = 0; i < N; i++) {
                if (isSynchronised){
                    semafor.acqurie();
                    cnt = cnt + 1;
                    semafor.release();
                } else {
                    cnt = cnt + 1;
                }
            }
        });

        Thread decrementator = new Thread(() -> {
            for (int i = 0; i < N; i++) {
                if (isSynchronised){
                    semafor.acqurie();
                    cnt = cnt - 1;
                    semafor.release();
                } else {
                    cnt = cnt - 1;
                }
            }
        });

        incrementator.start();
        decrementator.start();

        try {
            incrementator.join();
            decrementator.join();

            String synchronised = isSynchronised ? "synchronised" : "no synchronised";

            System.out.println("Result of " + synchronised +
                    " incrementing and decrementing the same number " +
                    N + " times is: " + cnt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
