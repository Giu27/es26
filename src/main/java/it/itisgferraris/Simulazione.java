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
    }

    @Override
    public void run() {
        
    }
}
