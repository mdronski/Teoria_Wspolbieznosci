package com.company.zadDom;

public class ZDom {
    public static void main(String[] args) {
        Wyscig synchronisedWyscig = new Wyscig();
        Wyscig noSynchronisedWyscig = new Wyscig(false);

        synchronisedWyscig.start();
        noSynchronisedWyscig.start();
    }
}
