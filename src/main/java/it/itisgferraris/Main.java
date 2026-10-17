package it.itisgferraris;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        Simulazione simulazione = new Simulazione(data);
        Interfaccia interfaccia = new Interfaccia(simulazione, data);
        interfaccia.start();
    }
}