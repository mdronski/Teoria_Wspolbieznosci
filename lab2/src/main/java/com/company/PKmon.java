package com.company;


import java.util.ArrayList;
import java.util.List;

public class PKmon {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        List<Producer> producers = new ArrayList<>();
        List<Consumer> consumers = new ArrayList<>();

        int n1 = 10;
        int n2 = 1;

        for (int i = 0; i < n1; i++) {
            producers.add(new Producer(buffer));
        }

        for (int i = 0; i < n2; i++) {
            consumers.add(new Consumer(buffer));
        }


        for (int i = 0; i < n1; i++) {
            producers.get(i).start();
        }

        for (int i = 0; i < n2; i++) {
            consumers.get(i).start();
        }



        for (int i = 0; i < n1; i++) {
            try {
                producers.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < n2; i++) {
            try {
                consumers.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}



class Producer extends Thread {
        private Buffer _buf;

        public Producer(Buffer buffer){
            this._buf = buffer;
        }

        public void run() {
            for (int i = 0; i < 100; ++i) {
                _buf.put(i);
            }
        }
    }

    class Consumer extends Thread {
        private Buffer _buf;


        public Consumer(Buffer buffer){
            this._buf = buffer;
        }

        public void run() {
            for (int i = 0; i < 100; ++i) {
                System.out.println(_buf.get());
            }
        }
    }





