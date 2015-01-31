package com.example.flo.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class wichtige_nummern extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wichtige_nummern);
        setupUI();

        List valueList = new ArrayList<String>();
        final String[] contact = new String[] {"ADAC", "ACE","AUTOMOBILCLUB","WERKSTATT","SPERRNUMMER_KREDITKARTE"};
        final String[] phoneNumber = new String[]{"*100#","*#0*#","*100#","*100#","*100#","116116"};
        for (int i = 0 ; i<contact.length ; i++){
            valueList.add( contact[i] + ": " + phoneNumber[i]);
        }
        //
        ListAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, valueList);
        final ListView lv = (ListView)findViewById(R.id.wichtige_nummern_listView);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(wichtige_nummern.this,TutorialListViewAcitivityAnzeige.class);
               // i.setClassName(getPackageName(), getPackageName()+ ".TutorialListViewAcitivityAnzeige");
                int clickedItemIndex = (int)lv.getAdapter().getItemId(position);
                i.putExtra("NAME", contact[clickedItemIndex]);
                i.putExtra("NUMMER", phoneNumber[clickedItemIndex]);
                startActivity(i);
            }
        });

    }

    private void setupUI() {
        Button callButton = (Button)findViewById(R.id.call_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("*100#")));
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:*100#"));
                startActivity(callIntent);
            }
        });
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
