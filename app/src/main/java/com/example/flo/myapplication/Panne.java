package com.example.flo.myapplication;

/**
 * Created by Flo on 22.06.2015.
 */
public class Panne {

    private String name;
    private String symptom;
    private String schritte;
    private int anzSchritte;
    private String bilder;
    private int id;
    private String faehrtNoch;

    public Panne(int id, String name, String symptom, String schritte, int anzSchritte, String bilder, String faehrtNoch) {
        this.id = id;
        this.name = name;
        this.symptom = symptom;
        this.schritte = schritte;
        this.anzSchritte = anzSchritte;
        this.bilder = bilder;
        this.faehrtNoch = faehrtNoch;
    }

    public Panne(String name, String symptom, String schritte, int anzSchritte, String bilder, String faehrtNoch) {
        this.name = name;
        this.symptom = symptom;
        this.schritte = schritte;
        this.anzSchritte = anzSchritte;
        this.bilder = bilder;
        this.faehrtNoch = faehrtNoch;
    }

    public Panne() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
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

    public String getBilder() {
        return bilder;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFaehrtNoch() {
        return faehrtNoch;
    }

    public void setFaehrtNoch(String faehrtNoch) {
        this.faehrtNoch = faehrtNoch;
    }
}
