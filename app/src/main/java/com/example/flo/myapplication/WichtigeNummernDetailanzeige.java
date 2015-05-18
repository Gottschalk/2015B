package com.example.flo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class WichtigeNummernDetailanzeige extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wichtige_nummern_detailanzeige);
        setupUI();

    }

    private void setupUI() {

        Intent i = getIntent();
        TextView nameTV = (TextView) findViewById(R.id.wichtige_nummern_detailanzeige_name);
        final TextView numberTV = (TextView) findViewById(R.id.wichtige_nummern_detailanzeige_nummer);
        final TextView streetTV = (TextView) findViewById(R.id.wichtige_nummern_detailanzeige_strasse);
        final TextView postalTV = (TextView) findViewById(R.id.wichtige_nummern_detailanzeige_plz);
        final TextView cityTV = (TextView) findViewById(R.id.wichtige_nummern_detailanzeige_stadt);

        final String name = i.getStringExtra("NAME");
        final String number = i.getStringExtra("NUMMER");
        final String street = i.getStringExtra("STREET");
        final String postal = i.getStringExtra("PLZ");
        final String city = i.getStringExtra("CITY");

        nameTV.setText(name);
        numberTV.setText(number);
        streetTV.setText(street);
        postalTV.setText(postal);
        cityTV.setText(city);


        Button callButton = (Button) findViewById(R.id.call_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("*100#")));
                Log.w("lajdföslkj", "adlkjölsflk");

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + numberTV.getText().toString()));
                startActivity(callIntent);
            }
        });

        Button changeButton = (Button) findViewById(R.id.wichtige_nummern_detailanzeige_bearbeiten_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("*100#")));
                Log.w("lajdföslkj", "adlkjölsflk");

            }
        });


        Button navigateButton = (Button) findViewById(R.id.wichtige_nummern_detailanzeige_navigation_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("*100#")));
              //  Intent callIntent = new Intent(Intent.ACTION_DIAL);
              //  callIntent.setData(Uri.parse("tel:" + numberTV.getText().toString()));
             //   startActivity(callIntent);
                Log.w("lajdföslkj", "adlkjölsflk");

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_tutorial_list_view_acitivity_anzeige, menu);
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
