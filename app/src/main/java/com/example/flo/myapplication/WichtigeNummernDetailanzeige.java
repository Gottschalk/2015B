package com.example.flo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class WichtigeNummernDetailanzeige extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wichtige_nummern_detailanzeige);

        Intent i = getIntent();
        //System.out.println(i.getStringExtra("selected"));
        TextView view = (TextView)findViewById(R.id.wichtige_nummern_anzeige_textView);
       // view.setText("fu");
        ((TextView)findViewById(R.id.wichtige_nummern_anzeige_textView)).setText("Name: "+i.getStringExtra("NAME") + " \n Nummer: " +i.getStringExtra("NUMMER"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial_list_view_acitivity_anzeige, menu);
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
