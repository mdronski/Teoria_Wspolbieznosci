package com.company.zadDom.Semafory;

import java.util.ArrayList;
import java.util.List;

public class Wyscig {
    private Integer cnt = 0;
    private ISemafor semafor;
    private int N = 10000;
    private boolean isSynchronised;
    int threadNumber = 1;

    public Wyscig(){
        this(true);
    }

    public Wyscig(boolean isSynchronised){
        this.semafor = new Semafor();
        this.isSynchronised = isSynchronised;
    }

    public Wyscig(ISemafor semafor){
        this.semafor = semafor;
        this.isSynchronised = true;
    }

    public Wyscig(ISemafor semafor, int threadNumber){
        this.semafor = semafor;
        this.isSynchronised = true;
        this.threadNumber = threadNumber;
    }



    public void start(){

        List<Thread> inceremtators = new ArrayList<>();
        List<Thread> deceremtators = new ArrayList<>();

        for (int n = 0; n < threadNumber; n++) {
            inceremtators.add(new Thread(() -> {
                for (int i = 0; i < N; i++) {
                    if (isSynchronised){
                        semafor.acquire();
                        cnt = cnt + 1;
                        semafor.release();
                    } else {
                        cnt = cnt + 1;
                    }
                }
            })
            );

            deceremtators.add(new Thread(() -> {
                for (int i = 0; i < N; i++) {
                    if (isSynchronised){
                        semafor.acquire();
                        cnt = cnt - 1;
                        semafor.release();
                    } else {
                        cnt = cnt - 1;
                    }
                }
            }));

        }


        for (int i = 0; i < threadNumber; i++) {
            inceremtators.get(i).start();
            deceremtators.get(i).start();
        }


        try {

            for (int i = 0; i < threadNumber; i++) {
                inceremtators.get(i).join();
                deceremtators.get(i).join();
            }

            String synchronised = isSynchronised ? "synchronised" : "no synchronised";

            System.out.println("Result of " + synchronised +
                    " incrementing and decrementing the same number " +
                    N + " times is: " + cnt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
