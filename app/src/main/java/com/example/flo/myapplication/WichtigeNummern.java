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



    private ListView listViewNummern;
    private ContactAdapter contactAdapter;
   // private ArrayList<Contact> contact_data = new ArrayList<Contact>();
    private DBHelper mDbHelper;
    private ArrayList<Contact> contact_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wichtige_nummern);
        setupUI();

        setupCustomList();


    }

    private void setupCustomList() {


        //contact_data.add(new Contact(R.drawable.ic_launcher, "ADAC", "*100#"));
        //  contact_data.add(new Contact(R.drawable.ic_launcher, "Werkstatt", "1155"));
        //contact_data.add(new Contact(R.drawable.ic_launcher, "Pannenhilfe", "*#0*#"));
        //contact_data.add(new Contact(R.drawable.ic_launcher, "ACE", "*100#"));


  //      mDbHelper.addContact(new Contact(1,R.drawable.ic_launcher, "ADAC" , "*100#"));
  //      mDbHelper.addContact(new Contact(2,R.drawable.ic_launcher, "adsfas" , "*100#"));
  //      mDbHelper.addContact(new Contact(3,R.drawable.ic_launcher, "AaadsfDAC" , "*100#"));
  //      mDbHelper.addContact(new Contact(4,R.drawable.ic_launcher, "babfdb" , "*100#"));

        mDbHelper = new DBHelper(this);
        contact_data= mDbHelper.getAllContacts();



        contactAdapter = new ContactAdapter(this,
                R.layout.listview_item_row, contact_data);


        listViewNummern = (ListView) findViewById(R.id.wichtige_nummern_listView);
        //header für listview setzen
        //   View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        //   listViewNummern.addHeaderView(header);

        listViewNummern.setAdapter(contactAdapter);
        listViewNummern.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(WichtigeNummern.this, WichtigeNummernDetailanzeige.class);
                int clickedItemIndex = (int) listViewNummern.getAdapter().getItemId(position);

                String contactName = contact_data.get(position).getName();
                String contactNumber = contact_data.get(position).getNumber();

                i.putExtra("NAME", contactName);
                i.putExtra("NUMMER", contactNumber);
                startActivity(i);
            }
        });
    }


   /*
     alte version ohne datenbank/speicherung von daten
    private void setupCustomList() {


        contact_data.add(new Contact(R.drawable.ic_launcher, "ADAC", "*100#"));
        contact_data.add(new Contact(R.drawable.ic_launcher, "Werkstatt", "1155"));
        contact_data.add(new Contact(R.drawable.ic_launcher, "Pannenhilfe", "*#0*#"));
        contact_data.add(new Contact(R.drawable.ic_launcher, "ACE", "*100#"));

        contactAdapter = new ContactAdapter(this,
                R.layout.listview_item_row, contact_data);


        listViewNummern = (ListView) findViewById(R.id.wichtige_nummern_listView);
        //header für listview setzen
        //   View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        //   listViewNummern.addHeaderView(header);

        listViewNummern.setAdapter(contactAdapter);
        listViewNummern.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(WichtigeNummern.this, WichtigeNummernDetailanzeige.class);
                int clickedItemIndex = (int) listViewNummern.getAdapter().getItemId(position);
                String contactName = contact_data.get(position).name;
                String contactNumber = contact_data.get(position).number;
                i.putExtra("NAME", contactName);
                i.putExtra("NUMMER", contactNumber);
                startActivity(i);
            }
        });
    }

    */

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

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // sign in the user ...
                        String name = nameInput.getText().toString();
                        String number = numberInput.getText().toString();

                        mDbHelper.addContact(new Contact(1,R.drawable.ic_launcher, name , number));
                        contact_data= mDbHelper.getAllContacts();
                        contactAdapter.notifyDataSetChanged();


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
