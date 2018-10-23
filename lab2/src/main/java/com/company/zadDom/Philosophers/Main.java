package com.company.zadDom.Philosophers;

public class Main {
    public static void main(String[] args) {

//        kazdy z algorytmow cechuje inne podejscie do problemu
//        oraz do wspolbieznego wykonania
//        rozwiazanie z uzyciem kelnera jest wedlug mnie najprostsze
//        jednak zarazem najmniej optymalne
//        rozwiazanie z uzyciem hierarchi zasobow wydaje sie dobrze przemyslane
//        lecz w ogolnym przypadku mozemy nie byc w stanie wyznaczyc relacji
//        czesciowego porzadku pomiedzy zasobami
//        rozwiazanie chandry/mistry jest wedlug mnie najlepsze,
//        cechuje go duza wspolbieznosc oraz prostota


        PhilosophersProblem philosophersProblem = new ChandryMisraSolution();
        philosophersProblem.startEating();
    }
}
