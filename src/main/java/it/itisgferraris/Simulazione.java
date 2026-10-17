package it.itisgferraris;

import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;

public class Simulazione extends Thread {
    private final int DURATA_SLEEP = 3000;
    private Data data;
    private Instant inizio, fine;
    private Duration durata;
    private ArrayList<Animale> animali = new ArrayList<>();
    private Ambiente ambiente;

    public Simulazione(Data data) {
        super();
        this.data = data;
        ambiente = new Ambiente();
    }

    public synchronized void wake() {
        notifyAll();
    }

    @Override
    public synchronized void run() {
        System.out.println("Simulazione creata");
        for (int i = 0; i < data.getTicks(); i++) {
            while (!data.getRunning()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
            ambiente.tick();
            for (Animale animale : animali) {
                animale.tick();
            }
            try {
                sleep(DURATA_SLEEP);
            } catch (InterruptedException ex) {}
        }
        data.setRunning(false);
        data.setTicks(0);
        System.out.println("Simulazione terminata");
        data.setTerminata(true);
    }
}