package com.example.flo.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;


public class WichtigeNummern extends ActionBarActivity {

    private DBHelper db;
    private ContactAdapter adapter;
    private ArrayList<Contact> contacts;
    // Tutorials:
    // Datenbank: http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
    // CustomListView: http://www.androidhive.info/2012/02/android-custom-listview-with-image-and-text/
    // Datenbank und Listview: http://www.mysamplecode.com/2012/07/android-listview-cursoradapter-sqlite.html


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wichtige_nummern);

        setupUI();

        db = new DBHelper(this);

        // falls zu viele testelemente in der db sind
      //   db.deleteAllContacts();

        contacts = db.getAllContacts();

        // Bei erster App-Benutzung leere DB abfangen
        if (contacts.size() == 0) {
            Log.e("Creating first contact ", "Creating contact");

            db.addContact(new Contact("ADAC", "*100#", "Teststrasse" , "98300" , "Musterstadt"));
            db.addContact(new Contact("Automobilclub Europa", "*100#", "Teststrasse" , "98300" , "Musterstadt"));
            db.addContact(new Contact("Werkstatt", "*100#", "Teststrasse" , "98300" , "Musterstadt"));
            db.addContact(new Contact("Mechaniker", "*100#", "Teststrasse" , "98300" , "Musterstadt"));

            contacts = db.getAllContacts();

        }

        for (Contact cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getNumber();
            // Writing Contacts to log
            Log.e("Name: ", log);
        }


        adapter = new ContactAdapter(this, R.layout.listview_item_row, contacts);
        final ListView listView = (ListView) findViewById(R.id.wichtige_nummern_listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(WichtigeNummern.this, WichtigeNummernDetailanzeige.class);
                int clickedItemIndex = (int) listView.getAdapter().getItemId(position);

                String contactName = contacts.get(position).getName();
                String contactNumber = contacts.get(position).getNumber();
                String contactStreet = contacts.get(position).getStreet();
                String contactPLZ = contacts.get(position).getPLZ();
                String contactCity = contacts.get(position).getCity();

                int contactId = contacts.get(position).getId();

                Log.w("###############nr:" , String.valueOf(contactId));

                i.putExtra("NAME", contactName);
                i.putExtra("NUMMER", contactNumber);
                i.putExtra("STREET", contactStreet);
                i.putExtra("PLZ", contactPLZ);
                i.putExtra("CITY", contactCity);
                i.putExtra("CONTACT_ID", contactId);
                startActivity(i);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(WichtigeNummern.this);
                builder.setTitle(("Element wirklich entfernen?"));
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button

                        int clickedItemIndex = (int) listView.getAdapter().getItemId(position);

                        Contact contact = contacts.get(position);
                        db.deleteContact(contact);
                        contacts.clear();
                        contacts.addAll(db.getAllContacts());
                        adapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });

    }

    private void setupUI() {
        Button nummerHinzufuegen = (Button) findViewById(R.id.nummer_hinzufuegen_button);
        nummerHinzufuegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showNewNumberEntryDialog();

            }
        });
    }

    private void showNewNumberEntryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.wichtige_nummern_notification, null);
        final EditText nameInput = (EditText) view.findViewById(R.id.notification_edit_name);
        final EditText numberInput = (EditText) view.findViewById(R.id.notification_edit_number);
        final EditText streetInput = (EditText) view.findViewById(R.id.notification_edit_street);
        final EditText plzInput = (EditText) view.findViewById(R.id.notification_edit_postal);
        final EditText cityInput = (EditText) view.findViewById(R.id.notification_edit_city);



        builder.setView(view)
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // sign in the user ...
                        String name = nameInput.getText().toString();
                        String number = numberInput.getText().toString();
                        String street = streetInput.getText().toString();
                        String plz = plzInput.getText().toString();
                        String city = cityInput.getText().toString();

                        if(name.equals("")){
                            name = "Kein Eintrag";
                        }
                        if(number.equals("")){
                            number = "Kein Eintrag";
                        }
                        if(street.equals("")){
                            street = "Kein Eintrag";
                        }
                        if(plz.equals("")){
                            plz = "Kein Eintrag";
                        }
                        if(city.equals("")){
                            city = "Kein Eintrag";
                        }

                        db.addContact(new Contact(name, number,street, plz, city));
                        contacts.clear();
                        contacts.addAll(db.getAllContacts());
                        adapter.notifyDataSetChanged();

                        for (Contact cn : contacts) {
                            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getNumber();
                            // Writing Contacts to log
                            Log.e("Name: ", log);
                        }
                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                }).setTitle("Neuen Kontakt erstellen");
        //  builder.create();
        builder.show();
    }


    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        contacts.clear();
        contacts.addAll(db.getAllContacts());
        adapter.notifyDataSetChanged();

        Log.w("balllfasdlj", "ON RESTUME!!!");


    }

    @Override
    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first

        // Activity being restarted from stopped state
        contacts.clear();
        contacts.addAll(db.getAllContacts());
        adapter.notifyDataSetChanged();

        Log.w("balllfasdlj", "ON RESTART!!!");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.menu_wichtige_nummern, menu);
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
