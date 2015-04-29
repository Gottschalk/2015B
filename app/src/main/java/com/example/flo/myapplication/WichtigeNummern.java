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


public class WichtigeNummern extends ActionBarActivity {

    DBHelper db;
    ContactAdapter adapter;
    ArrayList<Contact> contacts;
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
        // db.deleteAllContacts();

        contacts = db.getAllContacts();

        // Bei erster App-Benutzung leere DB abfangen
        if (contacts.size() == 0) {
            Log.e("Creating first contacts: ", "Creating first contacts..");

            db.addContact(new Contact("ADAC", "*100#"));
            db.addContact(new Contact("Automobilclub Europa", "*100#"));
            db.addContact(new Contact("Werkstatt", "*100#"));
            db.addContact(new Contact("Mechaniker", "*100#"));

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

                i.putExtra("NAME", contactName);
                i.putExtra("NUMMER", contactNumber);
                startActivity(i);
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


        builder.setView(view)
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // sign in the user ...
                        String name = nameInput.getText().toString();
                        String number = numberInput.getText().toString();

                        db.addContact(new Contact(name, number));
                        contacts = db.getAllContacts();
                        adapter.add(new Contact(name, number));
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
                });
        //  builder.create();
        builder.show();
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
