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

            // AUTOBATTERIE WECHSELN

            // AUTO WIRD LANGSAMER UND BLEIBT STEHEN

            // AUTO VERLIERT AN LEISTUNG, LAEUFT ABER NOCH IM NOTBETRIEB

            // AUTO STARTET NICHT
            // -> AUTO STARTET NICHT , ANLASSER DREHT ABER
            // -> AUTO STARTET NICHT, ANLASSER DREHT NICHT

            // AUSPUFF HAENGT HERUNTER

            // AUTOBATTERIE LEER

            // BESCHLAGENE SCHEINWERFER

            // BELEUCHTUNG FAELLT AUS

            // BREMSEN QUIETSCHEN BEI DER FAHRT

            // FAHRZEUG VERLIERT FLUESSIGKEITEN

            // FAHRZEUG ABSCHLEPPEN

            // GERAEUSCHE AUS DEM FAHRZEUG

            // GERAEUSCHE AUS DER RADGEGEND


            // KEIN BILD VORHANDEN STRING
            // String noPicture = "null";
            db.addPanne(new Panne("Reifen platt (name)", "Karosserie - Reifen platt (bauteil)", "Reifen platt (symptom)", reifenPlattAnleitung, 4, reifenPlattAnleitungBilder, "false"));
            db.addPanne(new Panne("Batterie leer(name)", "Elektrik - Batterie leer(bauteil)", "Batterie leer(symptom)", batterieLeerAnleitung, 3, batterieLeerAnleitungBilder, "false"));
            db.addPanne(new Panne("Kontrollampen leuchten(name)", "Anzeigen - Kontrollampen(bauteil)", "Kontrollampen leuchten(symptom)", kontrolllampenAnleitung, 2, kontrolllampenAnleitungBilder, "true"));
            db.addPanne(new Panne("Lenkung klemmt(name)", "Lenkung(bauteil)", "Lenkung klemmt(symptom)", lenkungKlemmtAnleitung, 2, lenkungKlemmtAnleitungBilder, "true"));

            // NEU

            // unicode:
            /*

            Ä,ä: \u00c4, \u00e4
            Ö,ö: \u00d6, \u00f6
            Ü,ü: \u00dc, \u00fc
            ß:   \u00df

            */

            // PANNE: ( String name, String bauteil, String symptom, String schritte, int anzSchritte, String bilder, String faehrtNoch )
            db.addPanne(new Panne("Autobatterie wechseln", "Elektrik", "Batterie leer", "" , 2, "null", "null"));
            db.addPanne(new Panne("Auto wird langsamer und bleibt stehen", " ", "Auto wird langsamer und bleibt stehen", "" , 2, "null", "null"));
            db.addPanne(new Panne("Auto verliert an Leistung, l\u00e4uft aber noch im Notbetrieb", " ", "Auto verliert an Leistung, l\u00e4uft aber noch im Notbetrieb", "" , 2, "null", "false"));
            db.addPanne(new Panne("Auto startet nicht", " ", "Auto startet nicht", "" , 2, "null", "false"));
            db.addPanne(new Panne("Auto verliert an Leistung, l\u00e4uft aber noch im Notbetrieb", " ", "Auto verliert an Leistung, l\u00e4uft aber noch im Notbetrieb", "" , 2, "null", "true"));
            db.addPanne(new Panne("Auspuff h\u00e4ngt herunter", " ", " ", "" , 2, "null", "true"));
            db.addPanne(new Panne("Autobatterie leer", " ", " ", "" , 2, "null", "false"));
            db.addPanne(new Panne("Beschlagene Scheinwerfer", " ", " ", "" , 2, "null", "true"));
            db.addPanne(new Panne("Beleuchtung f\u00e4llt aus", " ", " ", "" , 2, "null", "true"));
            db.addPanne(new Panne("Bremsen quietschen", " ", "Bremsen quietschen", "" , 2, "null", "true"));
            db.addPanne(new Panne("Fahrzeug verliert Fl\u00fcssigkeiten", " ", "Fahrzeug verliert Fl\u00fcssigkeiten", "" , 2, "null", "true"));
            db.addPanne(new Panne("Fahrzeug abschleppen", " ", " ", "" , 2, "null", "false"));


            pannen = db.getAllPannen();

        }

        for (Panne panne : pannen) {
            String log = "Id: " + panne.getId() + " ,Name: " + panne.getName() + " ,Symptom: " + panne.getSymptom() + " ,Bauteil: " + panne.getBauteil()
                    + " ,AnzahlSchritte: " + panne.getAnzSchritte() + " ,Schritte: " + panne.getSchritte() + " ,Bilder: " + panne.getBilder() + " ,faehrtNoch: " + panne.getFaehrtNoch();

        }
    }
}
