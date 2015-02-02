package com.example.flo.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
   private final String[] CONTACT = new String[] {"ADAC", "ACE","AUTOMOBILCLUB","WERKSTATT","SPERRNUMMER_KREDITKARTE"};
   private final String[] PHONE_NUMBER = new String[]{"*100#","*#0*#","*100#","*100#","*100#","116116"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wichtige_nummern);
        setupUI();


        listLength = CONTACT.length;
        for (int i = 0 ; i<CONTACT.length ; i++){
            valueList.add(CONTACT[i] + ": " + PHONE_NUMBER[i]);
        }
        //
        final ListView LISTVIEW_NUMMERN = (ListView)findViewById(R.id.wichtige_nummern_listView);
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, valueList);
        LISTVIEW_NUMMERN.setAdapter(adapter);

        LISTVIEW_NUMMERN.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(WichtigeNummern.this, WichtigeNummernDetailanzeige.class);
                int clickedItemIndex = (int) LISTVIEW_NUMMERN.getAdapter().getItemId(position);
                i.putExtra("NAME", CONTACT[clickedItemIndex]);
                i.putExtra("NUMMER", PHONE_NUMBER[clickedItemIndex]);
                startActivity(i);
            }
        });

    }

    private void setupUI() {
        Button nummerHinzufuegen = (Button)findViewById(R.id.nummer_hinzufuegen_button);
        nummerHinzufuegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPrompt();
             // Item zu liste hinzufügen
             //   valueList.add("test" +listLength++);
             //   adapter.notifyDataSetChanged();


            }
        });
    }

    private void showPrompt() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Nummer hinzufügen:");
        alert.setMessage("Nummer:");

// Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = String.valueOf(input.getText());
                // Do something with value!
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
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
