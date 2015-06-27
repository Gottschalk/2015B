package com.example.flo.myapplication;

/**
 * Created by Flo on 22.06.2015.
 */
public class Panne {

    private String name;
    private String bauteil;
    private String symptom;
    private String schritte;
    private int anzSchritte;
    private String bilder;
    private int id;

    public Panne(int id, String name, String bauteil, String symptom, String schritte, int anzSchritte, String bilder) {
        this.id = id;
        this.name = name;
        this.bauteil = bauteil;
        this.symptom = symptom;
        this.schritte = schritte;
        this.anzSchritte = anzSchritte;
        this.bilder = bilder;
    }

    public Panne(String name, String bauteil, String symptom, String schritte, int anzSchritte, String bilder) {
        this.name = name;
        this.bauteil = bauteil;
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

    public String getBauteil() {
        return bauteil;
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

    public String getBilder() {
        return bilder;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBauteil(String bauteil) {
        this.bauteil = bauteil;
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

    public void setBilder(String bilder) {
        this.bilder = bilder;
    }
}
