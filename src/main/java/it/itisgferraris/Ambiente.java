package it.itisgferraris;

import java.util.HashMap;

public class Ambiente implements Tickable{
    private HashMap<String, String> parametri;

    public Ambiente() {
        parametri = new HashMap<>();
    }
    
    public Ambiente(HashMap<String,String> parametri) {
        this.parametri = new HashMap<>(parametri);
    }

    public HashMap<String, String> getParametri() {
        return parametri;
    }

    public void setParametri(HashMap<String, String> parametri) {
        this.parametri.clear();
        this.parametri.putAll(parametri);
    }

    public String getParametro(String key) {
        return this.parametri.get(key);
    }

    public void setParametro(String key, String val) {
        this.parametri.put(key, val);
    }

    @Override
    public void tick() {

    }
}