package com.example.flo.myapplication;

/**
 * Created by Flo on 22.06.2015.
 */
public class Panne {

    private String name;
    private String ursache;
    private String symptom;
    private String schritte;
    private int anzSchritte;
    private int bilder;
    private int id;

    public Panne(int id, String name, String ursache, String symptom, String schritte, int anzSchritte, int bilder) {
        this.id = id;
        this.name = name;
        this.ursache = ursache;
        this.symptom = symptom;
        this.schritte = schritte;
        this.anzSchritte = anzSchritte;
        this.bilder = bilder;
    }

    public Panne(String name, String ursache, String symptom, String schritte, int anzSchritte, int bilder) {
        this.name = name;
        this.ursache = ursache;
        this.symptom = symptom;
        this.schritte = schritte;
        this.anzSchritte = anzSchritte;
        this.bilder = bilder;
    }

    public Panne() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrsache() {
        return name;
    }

    public String getSymptom() {
        return symptom;
    }

    public String getSchritte() {
        return schritte;
    }

    public int getAnzSchritte() {
        return anzSchritte;
    }

    public int getBilder() {
        return bilder;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrsache(String ursache) {
        this.ursache = ursache;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public void setSchritte(String schritte) {
        this.schritte = schritte;
    }

    public void setAnzSchritte(int anzSchritte) {
        this.anzSchritte = anzSchritte;
    }

    public void setBilder(int bilder) {
        this.bilder = bilder;
    }
}
