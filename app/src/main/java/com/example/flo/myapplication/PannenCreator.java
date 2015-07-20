package com.example.flo.myapplication;

import android.content.Context;

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

        // catch empty database on first usage
        if (pannen.size() == 0) {

            //  REIFEN PLATT //
            String reifenPlattAnleitung = "$ Wagen abstellen $ Handbremse ziehen $ Gang einlegen $ Wagenheber holen";
            String reifenPlattAnleitungBilder = "|ic_camera1|ic_kontakte1|ic_camera1|ic_kontakte1";


            // AUTOBATTERIE WECHSELN
            String autobatterieWechselnAnleitung = "$Motor und alle elektrischen Verbraucher (Radio, Licht, etc.) abschalten" +
                    "$Motorhaube öffnen und Mutter am Minuspol abschrauben. Jetzt das schwarze Verbindungskabel abziehen" +
                    "$Durch Lösen der Mutter von der Batterie das rote Kabel, das zum Pluspol läuft, entfernen" +
                    "$Jetzt Halterungsschrauben der Batterie entfernen" +
                    "$Jetzt kann die Batterie herausgenommen werden und eine neue Batterie eingesetzt werden. Batterie dabei nicht kippen!" +
                    "$Rotes Kabel wieder mit der Schraube an den Pluspol anbringen" +
                    "$Schwarzes Kabel am Minuspol anbringen und mit Schraube befestigen" +
                    "$Falls vorrätig, beide Pole  mit Polfett beschmieren";
            int autobatterieWechselnAnzSchritte = 8;
            String autobatterieWechselnBilder = "|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln2";

            // AUSPUFF HAENGT HERUNTER
            String auspuffHaengtAnleitung = "$Darauf achten, dass der Auspuff nicht mehr heiß ist" +
                    "$Ein Stück Draht oder Kabelbinder vorbereiten. Es können auch mehrere Kabelbinder miteinander verbunden werden." +
                    "$Draht oder Kabelbinder möglichst an Auspuffhalterung anbringen" +
                    "$Mit dieser provisorischen Lösung nur so geringe Strecke wie möglich zurücklegen und Schaden anschließend in einer Werkstatt beheben lassen.";
            int auspuffHaengtAnzSchritte = 4;
            String auspuffHaengtBilder = "|beheben_auspuff_haengt1|beheben_auspuff_haengt2|beheben_auspuff_haengt3|beheben_auspuff_haengt3";

            // BESCHLAGENE SCHEINWERFER
            String beschlageneScheinwerferAnleitung = "$Normalerweise verschwindet die Feuchtigkeit nach einer Weile wieder von selbst. Durch das Fahren mit Licht verdunstet das Wasser noch schneller." +
                    "$Um Feuchtigkeit zu vermeiden, sollte bei der Autowäsche mit dem Hochdruckreiniger nicht direkt auf die Scheinwerfer gezielt und mind. 30cm Abstand gehalten werden.";
            int beschlageneScheinwerferAnzSchritte = 2;
            String beschlageneScheinwerferBilder = "|beheben_beschlagene_scheinwerfer1|beheben_beschlagene_scheinwerfer1";

            // BREMSEN QUIETSCHEN
            String bremsenQuietschenAnleitung = "$Quietschende Bremsen können mehrere Ursachen haben. Falls die Ursache an den Bremsklötzen liegt, so ist es ratsam, die Reparatur einem erfahrenen Mechaniker in der Werkstatt zu überlassen, da die Bremsanlage ein sicherheitsrelevanter Bestandteil des Fahrzeuges ist." +
                    "$Ansonsten kann folgendes Vorgehen möglicherweise Abhilfe schaffen:" +
                    "$Überprüfen, ob die Handbremse richtig gelöst ist" +
                    "$Eventuell klebende Bremsbacken durch langsames Rückwärtsfahren lösen" +
                    "$Mehrmals stark bremsen";
            int bremsenQuietschenAnzSchritte = 5;
            String bremsenQuietschenBilder = "|beheben_bremsen_quietschen1|beheben_bremsen_quietschen2|beheben_bremsen_quietschen3|beheben_bremsen_quietschen4|beheben_bremsen_quietschen5";

            // FRONTSCHEINWERFER BIRNE WECHSELN
            String frontscheinwerferBirneWechselnAnleitung = "$Motorhaube öffnen" +
                    "$Pluspol der Autobatterie abklemmen. Dazu die Mutter am Pluspol lösen und das daran befindliche Kabel von der Batterie lösen" +
                    "$Zum Scheinwerfer, der defekt ist gehen und dessen Schutzklappe im Motorinnenraum abziehen oder abklappen." +
                    "$Stecker am Scheinwerferlicht abziehen. Falls vorhanden, Metallbügel vorsichtig zusammendrücken und nach hinten abschieben" +
                    "$Alte Birne herausziehen und neue Birne einsetzen. Wichtig: Birne nur mit einem Tuch oder mit Handschuhen anfassen." +
                    "$Alte Birne herausziehen und neue Birne einsetzen. Wichtig: Birne nur mit einem Tuch oder mit Handschuhen anfassen." +
                    "$Metallbügel und Stecker wieder anbringen." +
                    "$Schutzklappe raufstecken und Pluspol an Batterie wieder mit Mutter befestigen.";

            int frontscheinwerferBirneWechselnAnzSchritte = 5;
            String frontscheinwerferBirneWechselnBilder = "|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1";

            // KEIN BILD VORHANDEN STRING
            // String noPicture = "null";
            //    db.addPanne(new Panne("Reifen platt (name)", "Karosserie - Reifen platt (bauteil)", "Reifen platt (symptom)", reifenPlattAnleitung, 4, reifenPlattAnleitungBilder, "false"));

            // unicode:
            /*
            Ä,ä: \u00c4, \u00e4
            Ö,ö: \u00d6, \u00f6
            Ü,ü: \u00dc, \u00fc
            ß:   \u00df
            */

            // NEU
            // PANNE: ( String name, String bauteil, String symptom, String schritte, int anzSchritte, String bilder, String faehrtNoch )
            db.addPanne(new Panne("Autobatterie wechseln", " ", " ", autobatterieWechselnAnleitung, autobatterieWechselnAnzSchritte, autobatterieWechselnBilder, "false"));
            db.addPanne(new Panne("Auspuff hängt herunter", " ", " ", auspuffHaengtAnleitung, auspuffHaengtAnzSchritte, auspuffHaengtBilder, "false"));
            db.addPanne(new Panne("Beschlagene Scheinwerfer", " ", " ", beschlageneScheinwerferAnleitung, beschlageneScheinwerferAnzSchritte, beschlageneScheinwerferBilder, "false"));
            db.addPanne(new Panne("Bremsen quietschen", " ", " ", bremsenQuietschenAnleitung, bremsenQuietschenAnzSchritte, bremsenQuietschenBilder, "false"));
            db.addPanne(new Panne("Frontscheinwerfer Birne wechseln", " ", " ", frontscheinwerferBirneWechselnAnleitung, frontscheinwerferBirneWechselnAnzSchritte, frontscheinwerferBirneWechselnBilder, "false"));


            pannen = db.getAllPannen();

        }

        for (Panne panne : pannen) {
            String log = "Id: " + panne.getId() + " ,Name: " + panne.getName() + " ,Symptom: " + panne.getSymptom() + " ,Bauteil: " + panne.getBauteil()
                    + " ,AnzahlSchritte: " + panne.getAnzSchritte() + " ,Schritte: " + panne.getSchritte() + " ,Bilder: " + panne.getBilder() + " ,faehrtNoch: " + panne.getFaehrtNoch();

        }
    }
}
