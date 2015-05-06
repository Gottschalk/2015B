package com.example.flo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void setupUI() {
        Button gefahrenstelle_absichern_button = (Button) findViewById(R.id.gefahrenstelle_absichern_button);
        Button panne_beheben_button = (Button) findViewById(R.id.panne_beheben_button);
        Button werkstatt_finden_button = (Button) findViewById(R.id.werkstatt_finden_button);
        Button wichtige_nummern_button = (Button) findViewById(R.id.wichtige_nummern_button);
        Button flashlight_button = (Button) findViewById(R.id.flashlight_button_text);

        gefahrenstelle_absichern_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, GefahrenstelleAbsichern.class);
                startActivity(intent);
            }
        });

        panne_beheben_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, PanneBeheben.class);
                startActivity(intent);
            }
        });

        werkstatt_finden_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        wichtige_nummern_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, WichtigeNummern.class);
                startActivity(intent);
            }
        });


        // Register the onClick listener with the implementation above
        flashlight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Taschenlampe.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
