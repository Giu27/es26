package it.itisgferraris;

import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;

public class Simulazione extends Thread{
    private int ticks;
    private boolean running = false;
    private Instant inizio, fine;
    private Duration durata;
    private ArrayList<Animale> animali = new ArrayList<>();
    private Ambiente ambiente;

    public Simulazione() {
        super();
        ambiente = new Ambiente();
    }

    @Override
    public void run() {
        System.out.println("Simulazione creata");
        for (int i = 0; i < ticks; i++) {
            while (!running) {
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
        }
    }
}