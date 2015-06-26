package com.example.flo.myapplication;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Flo on 26.06.2015.
 */
public class PannenCreator {

    private Context context;
    private ArrayList<Panne> pannen;
    private PanneDBHelper db;

    public PannenCreator(Context context){

        this.context = context;

        Log.w("Pannencrator", "creator created");

       // savePannenToDB(context);
    }

    public void savePannenToDB(Context context) {

        Log.w("Pannencrator", "creator savePannenToDB");

        db = new PanneDBHelper(context);

        pannen = db.getAllPannen();

        // TODO RAUSMACHEN!!!!
    //    db.deleteAllPannen();

        // Bei erster App-Benutzung leere DB abfangen
        if (pannen.size() == 0) {
            Log.e("Creating first panne ", "Creating panne");


            String reifenPlattAnleitung = "$ Wagen abstellen $ Handbremse ziehen $ Gang einlegen $ Wagenheber holen";
            String batterieLeerAnleitung = "$ Plus pol an plus pol $ zweitwagen bal $ nix";
            String kontrolllampenAnleitung = "$ kontrollampe rot: $ kontrollampe blau";
            String lenkungKlemmtAnleitung = "$ Schluessel in Zuendschloss stecken $ Lenkrad drehen";


            db.addPanne(new Panne("Reifen platt (name)", "Karosserie - Reifen platt (bauteil)", "Reifen platt (symptom)", reifenPlattAnleitung, 1, R.id.icon));
            db.addPanne(new Panne("Batterie leer(name)", "Elektrik - Batterie leer(bauteil)", "Batterie leer(symptom)", batterieLeerAnleitung, 1, R.id.icon));
            db.addPanne(new Panne("Kontrollampen leuchten(name)", "Anzeigen - Kontrollampen(bauteil)", "Kontrollampen leuchten(symptom)", kontrolllampenAnleitung, 1, R.id.icon));
            db.addPanne(new Panne("Lenkung klemmt(name)", "Lenkung(bauteil)", "Lenkung klemmt(symptom)", lenkungKlemmtAnleitung, 1, R.id.icon));


            pannen = db.getAllPannen();

        }

        for (Panne panne : pannen) {
            String log = "Id: " + panne.getId() + " ,Name: " + panne.getName() + " ,Symptom: " + panne.getSymptom() + " ,Bauteil: " + panne.getBauteil()
                    + " ,AnzahlSchritte: " + panne.getAnzSchritte() + " ,Schritte: " + panne.getSchritte() + " ,Bilder: " + panne.getBilder();
            // Writing Contacts to log
            Log.e("Name: ", log);
        }
    }
}
