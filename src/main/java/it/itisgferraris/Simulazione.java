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

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public boolean getRunning() {
        return running;
    }

    public synchronized void setRunning(boolean r) {
        if (ticks == 0) {
            throw new IllegalStateException("Non puoi avviare senza aver impostato i tick di simulazione");
        }
        running = r;
        notify();
    }

    @Override
    public synchronized void run() {
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
        running = false;
        ticks = 0;
        System.out.println("Simulazione terminata");
    }
}