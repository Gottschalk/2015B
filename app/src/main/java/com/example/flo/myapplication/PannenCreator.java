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

    public PannenCreator(Context context) {

        this.context = context;

        // savePannenToDB(context);
    }

    public void savePannenToDB(Context context) {

        db = new PanneDBHelper(context);

        pannen = db.getAllPannen();

        // TODO RAUSMACHEN!!!!
            db.deleteAllPannen();

        // Bei erster App-Benutzung leere DB abfangen
        if (pannen.size() == 0) {
            Log.e("Creating first panne ", "Creating panne");


            //  REIFEN PLATT //
            String reifenPlattAnleitung = "$ Wagen abstellen $ Handbremse ziehen $ Gang einlegen $ Wagenheber holen";
            String reifenPlattAnleitungBilder = "|ic_camera1|ic_kontakte1|ic_camera1|ic_kontakte1";

            //  BATTERIE LEER //
            String batterieLeerAnleitung = "$ Plus pol an plus pol $ zweitwagen bal $ nix";
            String batterieLeerAnleitungBilder = "|ic_camera1|ic_kontakte1|ic_camera1";

            // KONTROLLLAMPEN LEUCHTEN
            String kontrolllampenAnleitung = "$ kontrollampe rot: $ kontrollampe blau";
            String kontrolllampenAnleitungBilder = "null";

            // LENKUNG KLEMMT //
            String lenkungKlemmtAnleitung = "$ Schluessel in Zuendschloss stecken $ Lenkrad drehen";
            String lenkungKlemmtAnleitungBilder = "|ic_camera1|ic_kontakte1";


            db.addPanne(new Panne("Reifen platt (name)", "Karosserie - Reifen platt (bauteil)", "Reifen platt (symptom)", reifenPlattAnleitung, 4, reifenPlattAnleitungBilder, "false"));
            db.addPanne(new Panne("Batterie leer(name)", "Elektrik - Batterie leer(bauteil)", "Batterie leer(symptom)", batterieLeerAnleitung, 3, batterieLeerAnleitungBilder, "false"));
            db.addPanne(new Panne("Kontrollampen leuchten(name)", "Anzeigen - Kontrollampen(bauteil)", "Kontrollampen leuchten(symptom)", kontrolllampenAnleitung, 2, kontrolllampenAnleitungBilder, "true"));
            db.addPanne(new Panne("Lenkung klemmt(name)", "Lenkung(bauteil)", "Lenkung klemmt(symptom)", lenkungKlemmtAnleitung, 2, lenkungKlemmtAnleitungBilder, "true"));


            pannen = db.getAllPannen();

        }

        for (Panne panne : pannen) {
            String log = "Id: " + panne.getId() + " ,Name: " + panne.getName() + " ,Symptom: " + panne.getSymptom() + " ,Bauteil: " + panne.getBauteil()
                    + " ,AnzahlSchritte: " + panne.getAnzSchritte() + " ,Schritte: " + panne.getSchritte() + " ,Bilder: " + panne.getBilder() + " ,faehrtNoch: " + panne.getFaehrtNoch();

        }
    }
}
