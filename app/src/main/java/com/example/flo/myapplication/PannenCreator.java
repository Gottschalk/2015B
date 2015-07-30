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

    }

    public void savePannenToDB(Context context) {

        db = new PanneDBHelper(context);
        pannen = db.getAllPannen();

        // for testing purposes delete all
        // db.deleteAllPannen();

        // catch empty database on first usage
        if (pannen.size() == 0) {

            //  REIFEN PLATT //
            String reifenPlattAnleitung = "$ Wagen abstellen $ Handbremse ziehen $ Gang einlegen $ Wagenheber holen";
            String reifenPlattAnleitungBilder = "|ic_camera1|ic_kontakte1|ic_camera1|ic_kontakte1";


            // AUTOBATTERIE WECHSELN
            String autobatterieWechselnAnleitung = "$Motor und alle elektrischen Verbraucher (Radio, Licht, etc.) abschalten" +
                    "$Motorhaube \u00f6ffnen und Mutter am Minuspol abschrauben. Jetzt das schwarze Verbindungskabel abziehen" +
                    "$Durch L\u00f6sen der Mutter von der Batterie das rote Kabel, das zum Pluspol l\u00e4uft, entfernen" +
                    "$Jetzt Halterungsschrauben der Batterie entfernen" +
                    "$Jetzt kann die Batterie herausgenommen werden und eine neue Batterie eingesetzt werden. Batterie dabei nicht kippen!" +
                    "$Rotes Kabel wieder mit der Schraube an den Pluspol anbringen" +
                    "$Schwarzes Kabel am Minuspol anbringen und mit Schraube befestigen" +
                    "$Falls vorr\u00e4tig, beide Pole  mit Polfett beschmieren";
            int autobatterieWechselnAnzSchritte = 8;
            String autobatterieWechselnBilder = "|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln1|beheben_autobatterie_wechseln2";

            // AUSPUFF HAENGT HERUNTER
            String auspuffHaengtAnleitung = "$Darauf achten, dass der Auspuff nicht mehr hei\u00df ist" +
                    "$Ein St\u00fcck Draht oder Kabelbinder vorbereiten. Es k\u00f6nnen auch mehrere Kabelbinder miteinander verbunden werden." +
                    "$Draht oder Kabelbinder m\u00f6glichst an Auspuffhalterung anbringen" +
                    "$Mit dieser provisorischen L\u00f6sung nur so geringe Strecke wie m\u00f6glich zur\u00fccklegen und Schaden anschlie\u00dfend in einer Werkstatt beheben lassen.";
            int auspuffHaengtAnzSchritte = 4;
            String auspuffHaengtBilder = "|beheben_auspuff_haengt1|beheben_auspuff_haengt2|beheben_auspuff_haengt3|beheben_auspuff_haengt3";

            // BESCHLAGENE SCHEINWERFER
            String beschlageneScheinwerferAnleitung = "$Normalerweise verschwindet die Feuchtigkeit nach einer Weile wieder von selbst. Durch das Fahren mit Licht verdunstet das Wasser noch schneller." +
                    "$Um Feuchtigkeit zu vermeiden, sollte bei der Autow\u00e4sche mit dem Hochdruckreiniger nicht direkt auf die Scheinwerfer gezielt und mind. 30cm Abstand gehalten werden.";
            int beschlageneScheinwerferAnzSchritte = 2;
            String beschlageneScheinwerferBilder = "|beheben_beschlagene_scheinwerfer1|beheben_beschlagene_scheinwerfer1";

            // BREMSEN QUIETSCHEN
            String bremsenQuietschenAnleitung = "$Quietschende Bremsen k\u00f6nnen mehrere Ursachen haben. Falls die Ursache an den Bremskl\u00f6tzen liegt, so ist es ratsam, die Reparatur einem erfahrenen Mechaniker in der Werkstatt zu \u00fcberlassen, da die Bremsanlage ein sicherheitsrelevanter Bestandteil des Fahrzeuges ist." +
                    "$Ansonsten kann folgendes Vorgehen m\u00f6glicherweise Abhilfe schaffen:" +
                    "$\u00fcberpr\u00fcfen, ob die Handbremse richtig gel\u00f6st ist" +
                    "$Eventuell klebende Bremsbacken durch langsames R\u00fcckw\u00e4rtsfahren l\u00f6sen" +
                    "$Mehrmals stark bremsen";
            int bremsenQuietschenAnzSchritte = 5;
            String bremsenQuietschenBilder = "|beheben_bremsen_quietschen1|beheben_bremsen_quietschen2|beheben_bremsen_quietschen3|beheben_bremsen_quietschen4|beheben_bremsen_quietschen5";

            // FRONTSCHEINWERFER BIRNE WECHSELN
            String frontscheinwerferBirneWechselnAnleitung = "$Motorhaube \u00f6ffnen" +
                    "$Pluspol der Autobatterie abklemmen. Dazu die Mutter am Pluspol l\u00f6sen und das daran befindliche Kabel von der Batterie l\u00f6sen" +
                    "$Zum Scheinwerfer, der defekt ist gehen und dessen Schutzklappe im Motorinnenraum abziehen oder abklappen." +
                    "$Stecker am Scheinwerferlicht abziehen. Falls vorhanden, Metallb\u00fcgel vorsichtig zusammendr\u00fccken und nach hinten abschieben" +
                    "$Alte Birne herausziehen und neue Birne einsetzen. Wichtig: Birne nur mit einem Tuch oder mit Handschuhen anfassen." +
                    "$Alte Birne herausziehen und neue Birne einsetzen. Wichtig: Birne nur mit einem Tuch oder mit Handschuhen anfassen." +
                    "$Metallb\u00fcgel und Stecker wieder anbringen." +
                    "$Schutzklappe raufstecken und Pluspol an Batterie wieder mit Mutter befestigen.";

            int frontscheinwerferBirneWechselnAnzSchritte = 8;
            String frontscheinwerferBirneWechselnBilder = "|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1|beheben_frontscheinwerfer_birne_wechseln1";

            // FAHRZEUG ABSCHLEPPEN
            String fahrzeugAbschleppenAnleitung =
                    "$In der Bedienungsanleitung den genauen Standort der Abschlepp\u00f6sen nachsehen. In der Regel befinden sich diese vorne und hinten rechts, manchmal aber von einer Abdeckung verdeckt." +
                            "$Abschleppseil in Abschlepp\u00f6sen einhaken" +
                            "$Z\u00fcndung einschalten" +
                            "$Getriebe in Leerlaufstellung bringen. Bei Automatikgetriebe in Neutralstellung bringen" +
                            "$Bei beiden Fahrzeugen Warnblinkanlage einschalten" +
                            "$W\u00e4hrend des Abschleppens sollte das Abschleppseil dauernd unter Spannung stehen";

            int fahrzeugAbschleppenAnzSchritte = 6;
            String fahrzeugAbschleppenBilder = "|beheben_abschleppen1|beheben_abschleppen1|beheben_abschleppen2|beheben_abschleppen3|beheben_abschleppen4|beheben_abschleppen5";


            // FAHRZEUG ANSCHLEPPEN ZUM MOTORSTART
            String fahrzeugAnschleppenAnleitung =
                    "$Wichtig: Nur bei Fahrzeugen mit manuellem Getriebe m\u00f6glich!!! Nicht l\u00e4nger als 50 Meter anschleppen, ansonsten k\u00f6nnte der Katalysator Schaden nehmen!" +
                            "$In der Betriebsanleitung nachsehen, ob anschleppen nicht untersagt ist" +
                            "$In der Bedienungsanleitung den genauen Standort der Abschlepp\u00f6sen nachsehen. In der Regel befinden sich diese vorne und hinten rechts, manchmal aber von einer Abdeckung verdeckt" +
                            "$Abschleppseil in Abschlepp\u00f6sen einhaken" +
                            "$Z\u00fcndung einschalten" +
                            "$2. oder 3. Gang einlegen und Kupplung durchdr\u00fccken" +
                            "$Bei beiden Fahrzeugen Warnblinkanlage einschalten" +
                            "$Sobald beide Fahrzeuge in Bewegung sind, langsam einkuppeln" +
                            "$Wenn der Motor angesprungen ist, Kuppplung treten und Gang herausnehmen" +
                            "$Etwas Gas geben (ca. 2000U/min). Wenn der Motor problemlos l\u00e4uft, kann die Fahrt fortgesetzt werden.";

            int fahrzeugAnschleppenAnzSchritte = 8;
            String fahrzeugAnschleppenBilder = "|beheben_anschleppen1|beheben_anschleppen2|beheben_anschleppen2|beheben_anschleppen3|beheben_anschleppen4|beheben_anschleppen5|beheben_anschleppen6|beheben_anschleppen7|beheben_anschleppen5|beheben_anschleppen10";


            // INNENSCHEIBE BESCHLAGEN
            String innenscheibeBeschlagenAnleitung =
                    "$L\u00fcftung einschalten und so einstellen, dass die Luft zur Frontscheibe gef\u00fchrt wird. Gebl\u00e4se auf h\u00f6chste Stufe und Heizung auf warm stellen." +
                            "$Falls vorhanden, Klimaanlage anschalten. Damit wird die Scheibe noch schneller frei." +
                            "$Falls vorhanden, auch die Heckscheibenheizung einschalten." +
                            "$Weiterhin ab und zu Scheibenwischer bet\u00e4tigen. Die Scheibe kann n\u00e4mlich auch von aussen beschlagen sein.";

            int innenscheibeBeschlagenAnzSchritte = 4;
            String innenscheibeBeschlagenBilder = "|beheben_innenscheibe_beschlagen1|beheben_innenscheibe_beschlagen1|beheben_innenscheibe_beschlagen1|beheben_innenscheibe_beschlagen1";

            // KONTROLLLAMPEN LEUCHTEN (ROT)
            String kontrolllampenRotAnleitung = "$ " + "$ ";
            int kontrolllampenRotAnzSchritte = 2;
            String kontrolllampenRotBilder = "|beheben_kontrollleuchten_rot1|beheben_kontrollleuchten_rot2";


            // KONTROLLLAMPEN LEUCHTEN (GELB)
            String kontrolllampenGelbAnleitung = "$ " + "$ ";
            int kontrolllampenGelbAnzSchritte = 2;
            String kontrolllampenGelbBilder = "|beheben_kontrollleuchten_gelb1|beheben_kontrollleuchten_gelb2";

            // REIFENPLATTEN MIT PANNENSPRAY DICHTEN
            String reifenPlattenPannensprayAnleitung =
                    "$Wichtig: Das Flicken mit Dichtmittel ist eine Notl\u00f6sung. Es sollte schnellstm\u00f6glichst der Reifen gewechselt oder eine Werkstatt aufgesucht werden." +
                            "$Reifen m\u00f6glichst von Fremdk\u00f6rpern befreien." +
                            "$Luft des Reifens ablassen, falls dieser nicht schon platt ist." +
                            "$Pannenspray auspacken und erw\u00e4rmen (mit H\u00e4nden oder Autoheizung)" +
                            "$Pannenspray gut sch\u00fctteln (ca 1-2 Minuten)" +
                            "$Spray auf das Ventil des Reifens aufsetzen und kompletten Inhalt einspr\u00fchen" +
                            "$Mithilfe des Kompressors aus dem Pannenset den Reifen aufpumpen" +
                            "$Falls die Luft im Reifen h\u00e4lt, kann vorsichtig (max. 80km/h) weitergefahren werden";

            int reifenPlattenPannensprayAnzSchritte = 8;
            String reifenPlattenPannensprayBilder = "|beheben_pannenspray1|beheben_pannenspray2|beheben_pannenspray3|beheben_pannenspray4|beheben_pannenspray4|beheben_pannenspray6|beheben_pannenspray7|beheben_pannenspray8";


            // REIFENWECHSEL
            String reifenwechselAnleitung =
                    "$Handbremse ziehen und Gang einlegen" +
                            "$Schrauben vor dem Aufbocken lockern, aber noch nicht herausdrehen" +
                            "$Wagenheber unter die Aufnahmepunke des Wagens schieben (s. Bedienungsanleitung) und aufbocken" +
                            "$Schrauben mit den Schraubenschl\u00fcssel herausdrehen" +
                            "$Rad abnehmen" +
                            "$Neues Rad aufstecken, Schrauben \u00fcber Kreuz einsetzen und leicht anziehen" +
                            "$Wagen leicht absenken: Das Ventil am Wagenheber so \u00f6ffnen, dass der Wagen etwas absinkt und das Rad blockiert" +
                            "$Falls Drehmomentschl\u00fcssel vorhanden, Drehmoment und Luftdruck in der Bedienungsanleitung nachsehen" +
                            "$Drehmomentschl\u00fcssel entsprechend einstellen" +
                            "$Anziehen: \u00fcber Kreuz die Radschrauben anziehen. Aber nur, bis der Drehmomentschl\u00fcssel h\u00f6rbar einrastet." +
                            "$Der Luftdruck soll beim leeren Auto 2,2 Bar betragen. Reifen entsprechend auff\u00fcllen. Fertig";

            int reifenwechselAnzSchritte = 11;
            String reifenwechselBilder = "|beheben_reifenwechsel1|beheben_reifenwechsel2|beheben_reifenwechsel3|beheben_reifenwechsel4|beheben_reifenwechsel5|beheben_reifenwechsel6|beheben_reifenwechsel7|beheben_reifenwechsel8|beheben_reifenwechsel9|beheben_reifenwechsel10|beheben_reifenwechsel11";


            // RUECKLEUCHTE BIRNE WECHSELN
            String rueckleuchteBirneWechselnAnleitung =
                    "$Kofferraum \u00f6ffnen" +
                            "$Abdeckung der R\u00fcckleuchten abschrauben" +
                            "$Abdeckung abziehen. Hierbei vorsichtig vorgehen und keine Gewalt anwenden" +
                            "$Auszutauschende Birne gegen den Uhrzeigersinn herausdrehen. Nach ca 1/4 Drehung kann die Birne herausgezogen werden." +
                            "$Neue Birne einsetzen. Dazu im Uhrzeigersinn ca 1/4 Drehung hineindrehen." +
                            "$Abdeckung wieder anbringen";

            int rueckleuchteBirneWechselnAnzSchritte = 6;
            String rueckleuchteBirneWechselnBilder = "|beheben_rueckleuchte_birne_wechseln1|beheben_rueckleuchte_birne_wechseln2|beheben_rueckleuchte_birne_wechseln3|beheben_rueckleuchte_birne_wechseln3|beheben_rueckleuchte_birne_wechseln3|beheben_rueckleuchte_birne_wechseln2";

            // STARTHILFE GEBEN
            String starthilfeAnleitung =
                    "$Motorhaube bei beiden Fahrzeugen \u00f6ffnen" +
                            "$Die Klemmen des roten Kabels mit den Pluspolen beider Batterien verbinden" +
                            "$Eine Klemme des schwarzen Kabels mit dem Minuspol der Batterie des Hilfsfahrzeuges verbinden " +
                            "$Die andere schwarze Klemme an einem Metallteil im Motorraum des Pannenfahrzeuges befestigen" +
                            "$Motor des Hilfsfahrzeuges starten" +
                            "$Startversuch beim Pannenfahrzeug" +
                            "$Startet der Motor, elektrische Verbraucher einschalten" +
                            "$Elektrische Verbraucher nach einiger Zeit wieder ausschalten" +
                            "$Schwarzes Kabel Klemmen entfernen" +
                            "$Rotes Kabel Klemmen entfernen" +
                            "$Mit dem Pannenfahrzeug sollte eine etwas l\u00e4ngere Fahrt unternommen werden, damit sich die Batterie aufladen kann";

            int starthilfeAnzSchritte = 11;
            String starthilfeBilder = "|beheben_starthilfe1|beheben_starthilfe2|beheben_starthilfe3|beheben_starthilfe4|beheben_starthilfe5|beheben_starthilfe5|beheben_starthilfe7|beheben_starthilfe7|beheben_starthilfe3|beheben_starthilfe2|beheben_starthilfe11";

            // SCHEIBENWISCHER WECHSELN GEBEN
            String scheibenwischerWechselnAnleitung =
                    "$Scheibenwischer hochklappen, sodass er von der Windschutzscheibe absteht" +
                            "$Gummiblatt l\u00f6sen. Dazu Plastikstopper zwischen Gummiwischer und Metallarm zusammendr\u00fccken und Gummiblatt abnehmen." +
                            "$Neuen Wischer einbauen. Dazu diesen einfach an der Stelle, wo der alte Wischer abgezogen wurde einschieben. Dann drehen, bis der Haken einschnappt." +
                            "$Wischer zur\u00fcck gegen Windschutzscheibe dr\u00fccken";

            int scheibenwischerWechselnAnzSchritte = 4;
            String scheibenwischerWechselnBilder = "|beheben_scheibenwischer_wechseln1|beheben_scheibenwischer_wechseln2|beheben_scheibenwischer_wechseln3|beheben_scheibenwischer_wechseln4";


            // TANK LEER
            String tankLeerAnleitung =
                    "$Bei \u00e4lteren Dieselfahrzeugen besser einen Pannenhelfer verst\u00e4ndigen. " +
                            "M\u00f6glicherweise muss die Kraftstoffanlage entl\u00fcftet werden. " +
                            "Bei neueren Fahrzeugen und Benzinern kann problemlos nachgetankt und dann weitergefahren werden." +
                            "Vorher allerdings in der Betriebsanleitung des Wagens nachsehen, ob Informationen zum Verhalten bei" +
                            " einem leergefahrenen Tank angegeben sind.";


            int tankLeerAnzSchritte = 1;
            String tankLeerBilder = "null";
            String noPicture = "null";

            // PANNE: ( String name, String bauteil, String symptom, String schritte, int anzSchritte, String bilder, String faehrtNoch )
            db.addPanne(new Panne("Autobatterie wechseln", " ", autobatterieWechselnAnleitung, autobatterieWechselnAnzSchritte, autobatterieWechselnBilder, "false"));
            db.addPanne(new Panne("Auspuff h\u00e4ngt herunter", " ", auspuffHaengtAnleitung, auspuffHaengtAnzSchritte, auspuffHaengtBilder, "true"));
            db.addPanne(new Panne("Beschlagene Scheinwerfer", "Beschlagene Scheinwerfer", beschlageneScheinwerferAnleitung, beschlageneScheinwerferAnzSchritte, beschlageneScheinwerferBilder, "true"));
            db.addPanne(new Panne("Bremsen quietschen", "Bremsen quietschen ", bremsenQuietschenAnleitung, bremsenQuietschenAnzSchritte, noPicture, "true"));
            db.addPanne(new Panne("Frontscheinwerfer Birne wechseln", " ", frontscheinwerferBirneWechselnAnleitung, frontscheinwerferBirneWechselnAnzSchritte, frontscheinwerferBirneWechselnBilder, "true"));
            db.addPanne(new Panne("Fahrzeug abschleppen", " ", fahrzeugAbschleppenAnleitung, fahrzeugAbschleppenAnzSchritte, fahrzeugAbschleppenBilder, "false"));
            db.addPanne(new Panne("Fahrzeug anschleppen zum Motorstart", " ", fahrzeugAnschleppenAnleitung, fahrzeugAnschleppenAnzSchritte, fahrzeugAnschleppenBilder, "false"));
            db.addPanne(new Panne("Innenscheibe beschlagen", "Innenscheibe beschlagen ", innenscheibeBeschlagenAnleitung, innenscheibeBeschlagenAnzSchritte, innenscheibeBeschlagenBilder, "true"));
            db.addPanne(new Panne("Kontrolllampen GELB", " ", kontrolllampenGelbAnleitung, kontrolllampenGelbAnzSchritte, kontrolllampenGelbBilder, "true"));
            db.addPanne(new Panne("Kontrolllampen ROT", " ", kontrolllampenRotAnleitung, kontrolllampenRotAnzSchritte, kontrolllampenRotBilder, "true"));

            db.addPanne(new Panne("Reifenwechsel", " ", reifenwechselAnleitung, reifenwechselAnzSchritte, reifenwechselBilder, "false"));
            db.addPanne(new Panne("Reifenplatten mit Pannenspray abdichten", " ", reifenPlattenPannensprayAnleitung, reifenPlattenPannensprayAnzSchritte, reifenPlattenPannensprayBilder, "false"));
            db.addPanne(new Panne("R\u00fcckleuchte Birne wechseln", " ", rueckleuchteBirneWechselnAnleitung, rueckleuchteBirneWechselnAnzSchritte, rueckleuchteBirneWechselnBilder, "true"));
            db.addPanne(new Panne("Scheibenwischerbl\u00e4tter wechseln", " ", scheibenwischerWechselnAnleitung, scheibenwischerWechselnAnzSchritte, scheibenwischerWechselnBilder, "true"));
            db.addPanne(new Panne("Starthilfe", " ", starthilfeAnleitung, starthilfeAnzSchritte, starthilfeBilder, "false"));
            db.addPanne(new Panne("Tank leer", " ", tankLeerAnleitung, tankLeerAnzSchritte, noPicture, "false"));

            pannen = db.getAllPannen();

        }

        for (Panne panne : pannen) {
            String log = "Id: " + panne.getId() + " ,Name: " + panne.getName() + " ,Symptom: " + panne.getSymptom()
                    + " ,AnzahlSchritte: " + panne.getNumberOfSteps() + " ,Schritte: " + panne.getSteps() + " ,Bilder: " + panne.getPictures() + " ,faehrtNoch: " + panne.getDriveAble();

        }
    }
}
