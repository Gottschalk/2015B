package com.example.flo.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class WichtigeNummern extends ActionBarActivity {

    private ContactDBHelper db;
    private ContactAdapter adapter;
    private ArrayList<Contact> contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wichtige_nummern);

        setupUI();

        db = new ContactDBHelper(this);

        // just for testing
        //   db.deleteAllContacts();

        contacts = db.getAllContacts();

        // catch empty database
        if (contacts.size() == 0) {

            db.addContact(new Contact("ADAC", "01802222222", "Paracelsusstrasse 1", "93053", "Regensburg"));
            db.addContact(new Contact("ARCD", "098440949", "", "", ""));
            db.addContact(new Contact("Auto und Reise Assistance", "09841409700", "", "", ""));
            db.addContact(new Contact("Automobilclub Europa", "0711530343536", "Schmidener Str. 227", "70374", "Stuttgart"));
            db.addContact(new Contact("Automobilclub Verkehr", "02219126910 ", "Theodor-Heuss-Ring 19-21", "50668", "Koeln"));
            db.addContact(new Contact("AvD", "0696606336", "Lyoner Strasse 16", "60528", "Frankfurt am Main"));
            db.addContact(new Contact("BAVC-Bruderhilfe", "0561709940", "Karthaeuserstr. 3 a", "34117", "Kassel"));
            db.addContact(new Contact("Europa Notruf", "112", "", "", ""));
            db.addContact(new Contact("Pannenhilfe / TCS ", "140", "", "", ""));
            db.addContact(new Contact("Polizei", "110", "", "", ""));
            db.addContact(new Contact("Notruf", "112", "", "", ""));
            db.addContact(new Contact("Mobil in Deutschland", "08920001610", "Elsenheimerstr. 45", "80867 ", "Muenchen"));
            db.addContact(new Contact("Verkehrsclub Deutschland", "0302803510", "Wallstrasse 58", "10179 ", "Berlin"));
            db.addContact(new Contact("Zentralruf der Autoversicherer GDV", "08002502600", "", "", ""));
            db.addContact(new Contact("Zentralruf der Autoversicherungsermittlung", "01803399888", "", "", ""));


            contacts = db.getAllContacts();
        }

        // set adapter for listview
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
                // Add positive and negative button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        String name = nameInput.getText().toString();
                        String number = numberInput.getText().toString();
                        String street = streetInput.getText().toString();
                        String plz = plzInput.getText().toString();
                        String city = cityInput.getText().toString();

                        if (name.equals("")) {
                            name = "Kein Eintrag";
                        }
                        if (number.equals("")) {
                            number = "Kein Eintrag";
                        }
                        if (street.equals("")) {
                            street = "Kein Eintrag";
                        }
                        if (plz.equals("")) {
                            plz = "Kein Eintrag";
                        }
                        if (city.equals("")) {
                            city = "Kein Eintrag";
                        }

                        // create new contact with input values
                        db.addContact(new Contact(name, number, street, plz, city));
                        contacts.clear();
                        contacts.addAll(db.getAllContacts());
                        adapter.notifyDataSetChanged();

                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                }).setTitle("Neuen Kontakt erstellen");
        builder.show();
    }


    @Override
    public void onResume() {
        super.onResume();

        contacts.clear();
        contacts.addAll(db.getAllContacts());
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        contacts.clear();
        contacts.addAll(db.getAllContacts());
        adapter.notifyDataSetChanged();

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
