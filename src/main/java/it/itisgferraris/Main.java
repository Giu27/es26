package it.itisgferraris;

public class Main {
    public static void main(String[] args) {
        Simulazione simulazione = new Simulazione();
        Interfaccia interfaccia = new Interfaccia(simulazione);
        interfaccia.start();
    }
}