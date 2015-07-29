package com.example.flo.myapplication;

/**
 * Created by Flo on 22.06.2015.
 */
public class Panne {

    private String name;
    private String symptom;
    private String steps;
    private int numberOfSteps;
    private String pictures;
    private int id;
    private String driveAble;

    public Panne(int id, String name, String symptom, String steps, int numberOfSteps, String pictures, String driveAble) {
        this.id = id;
        this.name = name;
        this.symptom = symptom;
        this.steps = steps;
        this.numberOfSteps = numberOfSteps;
        this.pictures = pictures;
        this.driveAble = driveAble;
    }

    public Panne(String name, String symptom, String steps, int numberOfSteps, String pictures, String driveAble) {
        this.name = name;
        this.symptom = symptom;
        this.steps = steps;
        this.numberOfSteps = numberOfSteps;
        this.pictures = pictures;
        this.driveAble = driveAble;
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

    public String getSteps() {
        return steps;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public String getPictures() {
        return pictures;
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

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getDriveAble() {
        return driveAble;
    }

    public void setDriveAble(String driveAble) {
        this.driveAble = driveAble;
    }
}
