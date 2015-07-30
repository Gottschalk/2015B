package com.example.flo.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class WichtigeNummernDetailanzeige extends Activity {

    private ContactDBHelper db;
    private int contactId;

    private TextView nameTV;
    private TextView numberTV;
    private TextView streetTV;
    private TextView postalTV;
    private TextView cityTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wichtige_nummern_detailanzeige);

        db = new ContactDBHelper(this);

        setupUI();

    }

    private void setupUI() {

        Intent i = getIntent();
        nameTV = (TextView) findViewById(R.id.wichtige_nummern_detailanzeige_name);
        numberTV = (TextView) findViewById(R.id.wichtige_nummern_detailanzeige_nummer);
        streetTV = (TextView) findViewById(R.id.wichtige_nummern_detailanzeige_strasse);
        postalTV = (TextView) findViewById(R.id.wichtige_nummern_detailanzeige_plz);
        cityTV = (TextView) findViewById(R.id.wichtige_nummern_detailanzeige_stadt);

        final String name = i.getStringExtra("NAME");
        final String number = i.getStringExtra("NUMMER");
        final String street = i.getStringExtra("STREET");
        final String postal = i.getStringExtra("PLZ");
        final String city = i.getStringExtra("CITY");
        contactId = i.getIntExtra("CONTACT_ID", -1);

        nameTV.setText(name);
        numberTV.setText(number);
        streetTV.setText(street);
        postalTV.setText(postal);
        cityTV.setText(city);


        Button callButton = (Button) findViewById(R.id.call_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + numberTV.getText().toString()));
                startActivity(callIntent);
            }
        });

        Button navigateButton = (Button) findViewById(R.id.wichtige_nummern_detailanzeige_navigation_button);
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String street = streetTV.getText().toString();
                String postal = postalTV.getText().toString();
                String city = cityTV.getText().toString();

                if (street.equals("Kein Eintrag")) {
                    street = "";
                }

                if (postal.equals("Kein Eintrag")) {
                    postal = "";
                }

                if (city.equals("Kein Eintrag")) {
                    city = "";
                }

                String searchQuery = street + " " + postal + " " + city;
                if (searchQuery.length() <= 3) {
                    Toast.makeText(getApplicationContext(), "Bitte Adressdaten aktualisieren!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("google.navigation:q=" + street + " " + postal + " " + city));
                    startActivity(i);
                }


            }
        });

        Button editDataButton = (Button) findViewById(R.id.wichtige_nummern_detailanzeige_bearbeiten_button);
        editDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createEditDataDialog();
            }
        });

    }

    private void createEditDataDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.wichtige_nummern_notification, null);
        final EditText nameInput = (EditText) view.findViewById(R.id.notification_edit_name);
        final EditText numberInput = (EditText) view.findViewById(R.id.notification_edit_number);
        final EditText streetInput = (EditText) view.findViewById(R.id.notification_edit_street);
        final EditText plzInput = (EditText) view.findViewById(R.id.notification_edit_postal);
        final EditText cityInput = (EditText) view.findViewById(R.id.notification_edit_city);


        builder.setView(view)
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // sign in the user ...
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

                        db.updateContact(new Contact(contactId, name, number, street, plz, city));

                        nameTV.setText(name);
                        numberTV.setText(number);
                        streetTV.setText(street);
                        postalTV.setText(plz);
                        cityTV.setText(city);

                        Toast.makeText(getApplicationContext(), "Daten gespeichert",
                                Toast.LENGTH_LONG).show();

                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                }).setTitle("Kontakt bearbeiten");
        //  builder.create();
        builder.show();
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
