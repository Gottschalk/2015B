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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class WichtigeNummern extends ActionBarActivity {

   private ArrayList<String> valueList = new ArrayList<String>();
   private int listLength;
   private ArrayAdapter adapter;
   //private  String[] contact = new String[] {"ADAC", "ACE","AUTOMOBILCLUB","WERKSTATT","SPERRNUMMER_KREDITKARTE"};
  // private  String[] phoneNumber = new String[]{"*100#","*#0*#","*100#","*100#","*100#","116116"};
   private ListView LISTVIEW_NUMMERN;
   private ArrayList<String> contact;
   private ArrayList<String> phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wichtige_nummern);
        setupUI();

        contact = new ArrayList<String>();
        contact.add("ADAC");
        contact.add("ACE");
        phoneNumber = new ArrayList<String>();
        phoneNumber.add("*100#");
        phoneNumber.add("*100#");

        LISTVIEW_NUMMERN = (ListView)findViewById(R.id.wichtige_nummern_listView);
        for (int i = 0 ; i< contact.size() ; i++){
            valueList.add(contact.get(i) + ": " + phoneNumber.get(i));
        }
        //
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, valueList);
        LISTVIEW_NUMMERN.setAdapter(adapter);

        LISTVIEW_NUMMERN.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(WichtigeNummern.this, WichtigeNummernDetailanzeige.class);
                int clickedItemIndex = (int) LISTVIEW_NUMMERN.getAdapter().getItemId(position);
                i.putExtra("NAME", contact.get(clickedItemIndex));
                i.putExtra("NUMMER", phoneNumber.get(clickedItemIndex));
                startActivity(i);
            }
        });

    }

    private void updateList() {

    }

    private void setupUI() {
        Button nummerHinzufuegen = (Button)findViewById(R.id.nummer_hinzufuegen_button);
        nummerHinzufuegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showCustomPrompt();

            }
        });
    }

    private void showCustomPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.wichtige_nummern_notification, null);
        final EditText nameInput = (EditText)view.findViewById(R.id.notification_edit_name);
        final  EditText numberInput = (EditText)view.findViewById(R.id.notification_edit_number);

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

                        contact.add(name);
                        phoneNumber.add(number);
                        valueList.add(name + ":" + number);
                        adapter.notifyDataSetChanged();

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
        getMenuInflater().inflate(R.menu.menu_wichtige_nummern, menu);
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
