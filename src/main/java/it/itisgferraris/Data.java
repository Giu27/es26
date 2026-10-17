package it.itisgferraris;

public class Data {
    private int ticks = 0;
    private boolean running = false;
    private boolean terminata = false;

    public Data() {

    }

    public int getTicks() {
        return ticks;
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
    }

    public boolean getTerminata() {
        return terminata;
    }

    public void setTerminata(boolean terminata) {
        this.terminata = terminata;
    }
}
