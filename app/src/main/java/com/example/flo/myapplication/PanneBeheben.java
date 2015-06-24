package com.example.flo.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class PanneBeheben extends ActionBarActivity {

    private FragmentTabHost mTabHost;
    private PanneDBHelper db;
    private PanneAdapter adapter;
    private ArrayList<Panne> pannen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panne_beheben);

        setupTabView();
        setupDB();
    }

    private void setupDB() {
        db = new PanneDBHelper(this);



        // falls zu viele testelemente in der db sind
        //   db.deleteAllContacts();

        pannen = db.getAllPannen();
        db.deleteAllPannen();

        // Bei erster App-Benutzung leere DB abfangen
        if (pannen.size() == 0) {
            Log.e("Creating first panne ", "Creating panne");

            db.addPanne(new Panne("Reifen platt (name)", "Karosserie - Reifen platt (bauteil)", "Reifen platt (symptom)", "Schritt 1: ... ", 1, R.id.icon));
            db.addPanne(new Panne("Batterie leer(name)", "Elektrik - Batterie leer(bauteil)", "Batterie leer(symptom)", "Schritt 1: ... ", 1, R.id.icon));
            db.addPanne(new Panne("Kontrollampen leuchten(name)", "Anzeigen - Kontrollampen(bauteil)", "Kontrollampen leuchten(symptom)", "Schritt 1: ... ", 1, R.id.icon));


            pannen = db.getAllPannen();

        }

        for (Panne panne : pannen) {
            String log = "Id: " + panne.getId() + " ,Name: " + panne.getName() + " ,Symptom: " + panne.getSymptom() + " ,Bauteil: " + panne.getBauteil()
                    + " ,AnzahlSchritte: " + panne.getAnzSchritte() + " ,Schritte: " + panne.getSchritte() + " ,Bilder: " + panne.getBilder();
            // Writing Contacts to log
            Log.e("Name: ", log);
        }
    }

    private void setupTabView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Name", null),
                PanneBehebenFragmentNameTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Bauteil", null),
                PanneBehebenFragmentBauteilTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Symptom", null),
                PanneBehebenFragmentSymptomTab.class, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_panne_beheben, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
