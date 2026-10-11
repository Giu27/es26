package it.itisgferraris;

import java.util.HashMap;

public class Animale {
    private String nome;
    private int age;
    private HashMap<String, String> parametri; //Dizionario che contiene le informazioni specifiche della specie

    public Animale(String nome, int age, HashMap<String,String> parametri) {
        this.nome = nome;
        this.age = age;
        this.parametri = new HashMap<>(parametri);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
}
