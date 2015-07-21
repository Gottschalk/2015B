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
                    "$Motorhaube �ffnen und Mutter am Minuspol abschrauben. Jetzt das schwarze Verbindungskabel abziehen" +
                    "$Durch L�sen der Mutter von der Batterie das rote Kabel, das zum Pluspol l�uft, entfernen" +
                    "$Jetzt Halterungsschrauben der Batterie entfernen" +
                    "$Jetzt kann die Batterie herausgenommen werden und eine neue Batterie eingesetzt werden. Batterie dabei nicht kippen!" +
                    "$Rotes Kabel wieder mit der Schraube an den Pluspol anbringen" +
                    "$Schwarzes Kabel am Minuspol anbringen und mit Schraube befestigen" +
                    "$Falls vorr�tig, beide Pole  mit Polfett beschmieren";
            int autobatterieWechselnAnzSchritte = 8;
            String autobatterieWechselnBilder = "|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln2";

            // AUSPUFF HAENGT HERUNTER
            String auspuffHaengtAnleitung = "$Darauf achten, dass der Auspuff nicht mehr hei� ist" +
                    "$Ein St�ck Draht oder Kabelbinder vorbereiten. Es k�nnen auch mehrere Kabelbinder miteinander verbunden werden." +
                    "$Draht oder Kabelbinder m�glichst an Auspuffhalterung anbringen" +
                    "$Mit dieser provisorischen L�sung nur so geringe Strecke wie m�glich zur�cklegen und Schaden anschlie�end in einer Werkstatt beheben lassen.";
            int auspuffHaengtAnzSchritte = 4;
            String auspuffHaengtBilder = "|beheben_auspuff_haengt1|beheben_auspuff_haengt2|beheben_auspuff_haengt3|beheben_auspuff_haengt3";

            // BESCHLAGENE SCHEINWERFER
            String beschlageneScheinwerferAnleitung = "$Normalerweise verschwindet die Feuchtigkeit nach einer Weile wieder von selbst. Durch das Fahren mit Licht verdunstet das Wasser noch schneller." +
                    "$Um Feuchtigkeit zu vermeiden, sollte bei der Autow�sche mit dem Hochdruckreiniger nicht direkt auf die Scheinwerfer gezielt und mind. 30cm Abstand gehalten werden.";
            int beschlageneScheinwerferAnzSchritte = 2;
            String beschlageneScheinwerferBilder = "|beheben_beschlagene_scheinwerfer1|beheben_beschlagene_scheinwerfer1";

            // BREMSEN QUIETSCHEN
            String bremsenQuietschenAnleitung = "$Quietschende Bremsen k�nnen mehrere Ursachen haben. Falls die Ursache an den Bremskl�tzen liegt, so ist es ratsam, die Reparatur einem erfahrenen Mechaniker in der Werkstatt zu �berlassen, da die Bremsanlage ein sicherheitsrelevanter Bestandteil des Fahrzeuges ist." +
                    "$Ansonsten kann folgendes Vorgehen m�glicherweise Abhilfe schaffen:" +
                    "$�berpr�fen, ob die Handbremse richtig gel�st ist" +
                    "$Eventuell klebende Bremsbacken durch langsames R�ckw�rtsfahren l�sen" +
                    "$Mehrmals stark bremsen";
            int bremsenQuietschenAnzSchritte = 5;
            String bremsenQuietschenBilder = "|beheben_bremsen_quietschen1|beheben_bremsen_quietschen2|beheben_bremsen_quietschen3|beheben_bremsen_quietschen4|beheben_bremsen_quietschen5";

            // FRONTSCHEINWERFER BIRNE WECHSELN
            String frontscheinwerferBirneWechselnAnleitung = "$Motorhaube �ffnen" +
                    "$Pluspol der Autobatterie abklemmen. Dazu die Mutter am Pluspol l�sen und das daran befindliche Kabel von der Batterie l�sen" +
                    "$Zum Scheinwerfer, der defekt ist gehen und dessen Schutzklappe im Motorinnenraum abziehen oder abklappen." +
                    "$Stecker am Scheinwerferlicht abziehen. Falls vorhanden, Metallb�gel vorsichtig zusammendr�cken und nach hinten abschieben" +
                    "$Alte Birne herausziehen und neue Birne einsetzen. Wichtig: Birne nur mit einem Tuch oder mit Handschuhen anfassen." +
                    "$Alte Birne herausziehen und neue Birne einsetzen. Wichtig: Birne nur mit einem Tuch oder mit Handschuhen anfassen." +
                    "$Metallb�gel und Stecker wieder anbringen." +
                    "$Schutzklappe raufstecken und Pluspol an Batterie wieder mit Mutter befestigen.";

            int frontscheinwerferBirneWechselnAnzSchritte = 8;
            String frontscheinwerferBirneWechselnBilder = "|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1";

            // FAHRZEUG ABSCHLEPPEN
            String fahrzeugAbschleppenAnleitung =
                    "$In der Bedienungsanleitung den genauen Standort der Abschlepp�sen nachsehen. In der Regel befinden sich diese vorne und hinten rechts, manchmal aber von einer Abdeckung verdeckt." +
                            "$Abschleppseil in Abschlepp�sen einhaken" +
                            "$Z�ndung einschalten" +
                            "$Getriebe in Leerlaufstellung bringen. Bei Automatikgetriebe in Neutralstellung bringen" +
                            "$Bei beiden Fahrzeugen Warnblinkanlage einschalten" +
                            "$W�hrend des Abschleppens sollte das Abschleppseil dauernd unter Spannung stehen";

            int fahrzeugAbschleppenAnzSchritte = 6;
            String fahrzeugAbschleppenBilder = "|beheben_abschleppen1|beheben_abschleppen1|beheben_abschleppen2|beheben_abschleppen3|beheben_abschleppen4|beheben_abschleppen5";


            // FAHRZEUG ANSCHLEPPEN ZUM MOTORSTART
            String fahrzeugAnschleppenAnleitung =
                    "$Wichtig: Nur bei Fahrzeugen mit manuellem Getriebe m�glich!!! Nicht l�nger als 50 Meter anschleppen, ansonsten k�nnte der Katalysator Schaden nehmen!" +
                            "$In der Betriebsanleitung nachsehen, ob anschleppen nicht untersagt ist" +
                            "$In der Bedienungsanleitung den genauen Standort der Abschlepp�sen nachsehen. In der Regel befinden sich diese vorne und hinten rechts, manchmal aber von einer Abdeckung verdeckt" +
                            "$Abschleppseil in Abschlepp�sen einhaken" +
                            "$Z�ndung einschalten" +
                            "$2. oder 3. Gang einlegen und Kupplung durchdr�cken"+
                            "$Bei beiden Fahrzeugen Warnblinkanlage einschalten" +
                            "$Sobald beide Fahrzeuge in Bewegung sind, langsam einkuppeln" +
                            "$Wenn der Motor angesprungen ist, Kuppplung treten und Gang herausnehmen" +
                            "$Etwas Gas geben (ca. 2000U/min). Wenn der Motor problemlos l�uft, kann die Fahrt fortgesetzt werden.";

            int fahrzeugAnschleppenAnzSchritte = 8;
            String fahrzeugAnschleppenBilder = "|beheben_anschleppen1|beheben_anschleppen2|beheben_anschleppen2|beheben_anschleppen3|beheben_anschleppen4|beheben_anschleppen5|beheben_anschleppen6|beheben_anschleppen7|beheben_anschleppen5|beheben_anschleppen10";








            // RUECKLEUCHTE BIRNE WECHSELN
            String rueckleuchteBirneWechselnAnleitung =
                    "$Kofferraum �ffnen" +
                            "$Abdeckung der R�ckleuchten abschrauben" +
                            "$Abdeckung abziehen. Hierbei vorsichtig vorgehen und keine Gewalt anwenden" +
                            "$Auszutauschende Birne gegen den Uhrzeigersinn herausdrehen. Nach ca � Drehung kann die Birne herausgezogen werden." +
                            "$Neue Birne einsetzen. Dazu im Uhrzeigersinn ca � Drehung hineindrehen." +
                            "$Abdeckung wieder anbringen";

            int rueckleuchteBirneWechselnAnzSchritte = 6;
            String rueckleuchteBirneWechselnBilder = "|beheben_rueckleuchte_birne_wechseln1|beheben_rueckleuchte_birne_wechseln2|beheben_rueckleuchte_birne_wechseln3|beheben_rueckleuchte_birne_wechseln3|beheben_rueckleuchte_birne_wechseln3|beheben_rueckleuchte_birne_wechseln2";


            // KEIN BILD VORHANDEN STRING
            //    db.addPanne(new Panne("Reifen platt (name)", "Karosserie - Reifen platt (bauteil)", "Reifen platt (symptom)", reifenPlattAnleitung, 4, reifenPlattAnleitungBilder, "false"));

            // unicode:
            /*
            �,�: \u00c4, \u00e4
            �,�: \u00d6, \u00f6
            �,�: \u00dc, \u00fc
            �:   \u00df
            */

            String noPicture = "null";

            // NEU
            // PANNE: ( String name, String bauteil, String symptom, String schritte, int anzSchritte, String bilder, String faehrtNoch )
            db.addPanne(new Panne("Autobatterie wechseln", " ", " ", autobatterieWechselnAnleitung, autobatterieWechselnAnzSchritte, autobatterieWechselnBilder, "false"));
            db.addPanne(new Panne("Auspuff h�ngt herunter", " ", " ", auspuffHaengtAnleitung, auspuffHaengtAnzSchritte, auspuffHaengtBilder, "false"));
            db.addPanne(new Panne("Beschlagene Scheinwerfer", " ", " ", beschlageneScheinwerferAnleitung, beschlageneScheinwerferAnzSchritte, beschlageneScheinwerferBilder, "false"));
            db.addPanne(new Panne("Bremsen quietschen", " ", " ", bremsenQuietschenAnleitung, bremsenQuietschenAnzSchritte, noPicture, "false"));
            db.addPanne(new Panne("Frontscheinwerfer Birne wechseln", " ", " ", frontscheinwerferBirneWechselnAnleitung, frontscheinwerferBirneWechselnAnzSchritte, frontscheinwerferBirneWechselnBilder, "false"));
            db.addPanne(new Panne("Fahrzeug abschleppen", " ", " ", fahrzeugAbschleppenAnleitung, fahrzeugAbschleppenAnzSchritte, fahrzeugAbschleppenBilder, "false"));
            db.addPanne(new Panne("Fahrzeug anschleppen zum Motorstart", " ", " ", fahrzeugAnschleppenAnleitung, fahrzeugAnschleppenAnzSchritte, fahrzeugAnschleppenBilder, "false"));




            db.addPanne(new Panne("R�ckleuchte Birne wechseln", " ", " ", rueckleuchteBirneWechselnAnleitung, rueckleuchteBirneWechselnAnzSchritte, rueckleuchteBirneWechselnBilder, "false"));

            pannen = db.getAllPannen();

        }

        for (Panne panne : pannen) {
            String log = "Id: " + panne.getId() + " ,Name: " + panne.getName() + " ,Symptom: " + panne.getSymptom() + " ,Bauteil: " + panne.getBauteil()
                    + " ,AnzahlSchritte: " + panne.getAnzSchritte() + " ,Schritte: " + panne.getSchritte() + " ,Bilder: " + panne.getBilder() + " ,faehrtNoch: " + panne.getFaehrtNoch();

        }
    }
}
