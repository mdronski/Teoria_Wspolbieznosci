package com.company;

import java.util.LinkedList;
import java.util.List;

public class Buffer {
    private final int max_size = 100;
    private int size = 0;
    private List<Integer> buf = new LinkedList<>();

    public synchronized void put(int i) {
        try {
            while (size == max_size) {
                wait();
            }
            buf.add(i);
            size = size + 1;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized int get() {
        try {
            while (buf.size() == 0){
                wait();
            }
            int tmp = buf.remove(size - 1);
            size = size -1;
            notifyAll();
            return tmp;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
