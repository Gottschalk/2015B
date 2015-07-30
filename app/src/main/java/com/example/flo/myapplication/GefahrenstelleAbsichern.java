package com.example.flo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class GefahrenstelleAbsichern extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gefahrenstelle_absichern);

        setupUI();
    }

    private void setupUI() {

        final ImageButton autobahn = (ImageButton) findViewById(R.id.gefahrenstelle_absichern_autobahn_button);
        ImageButton kurve = (ImageButton) findViewById(R.id.gefahrenstelle_absichern_kurve_button);
        ImageButton innerorts = (ImageButton) findViewById(R.id.gefahrenstelle_absichern_innerorts_button);
        ImageButton ausserorts = (ImageButton) findViewById(R.id.gefahrenstelle_absichern_aussererorts_button);

        autobahn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GefahrenstelleAbsichern.this, GefahrenstelleAbsichernAnleitung.class);
                intent.putExtra("Ort", "autobahn");
                startActivity(intent);
            }
        });

        kurve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GefahrenstelleAbsichern.this, GefahrenstelleAbsichernAnleitung.class);
                intent.putExtra("Ort", "kurve");
                startActivity(intent);
            }
        });

        innerorts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GefahrenstelleAbsichern.this, GefahrenstelleAbsichernAnleitung.class);
                intent.putExtra("Ort", "innerorts");
                startActivity(intent);
            }
        });

        ausserorts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GefahrenstelleAbsichern.this, GefahrenstelleAbsichernAnleitung.class);
                intent.putExtra("Ort", "ausserorts");
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.menu_gefahrenstelle_absichern, menu);
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
