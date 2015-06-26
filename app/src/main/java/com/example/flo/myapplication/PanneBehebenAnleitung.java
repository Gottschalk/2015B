package com.example.flo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class PanneBehebenAnleitung extends ActionBarActivity {

    private int currentStep;
    private int maxSteps;
    private TextView step;
    private TextView toDo;
    private ArrayList<String> pannenAnleitung;
    private ImageView helpImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panne_beheben_anleitung);

        Intent intent = getIntent();

        String name = intent.getStringExtra("NAME");
        Log.w("pannebeheben anleitung ", name);

        String pannenAnleitungString = intent.getStringExtra("SCHRITTE");
        Log.w("pannebeheben anleitung ", name);


         pannenAnleitung = createPannenStepsFromDBString(pannenAnleitungString);

        for (int i = 0; i < pannenAnleitung.size(); i++) {
            Log.e("%%%%%steps: " , pannenAnleitung.get(i));

        }

          maxSteps = pannenAnleitung.size();
          currentStep = 1;

        Button nextButton = (Button)findViewById(R.id.panne_beheben_nextStepButton);
        Button previousButton = (Button)findViewById(R.id.panne_beheben_previousStepButton);

         helpImage = (ImageView)findViewById(R.id.panneBeheben_anleitung_bild);
         step = (TextView)findViewById(R.id.panne_beheben_stepTextview);
         toDo = (TextView)findViewById(R.id.panneBeheben_anleitung_text);

        step.setText("Schritt: " + currentStep + " / " + maxSteps);
        toDo.setText(pannenAnleitung.get(currentStep));

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentStep < maxSteps) {

                    currentStep++;
                    step.setText("Schritt: " + currentStep + " / " + maxSteps);
                    step.setText("Schritt: " + currentStep + " / " + maxSteps);
                    toDo.setText(pannenAnleitung.get(currentStep-1));

                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentStep > 1){

                    currentStep--;
                    step.setText("Schritt: " + currentStep + " / " + maxSteps);
                    step.setText("Schritt: " + currentStep + " / " + maxSteps);
                    toDo.setText(pannenAnleitung.get(currentStep-1));

                }
            }
        });

    }

    private ArrayList<String> createPannenStepsFromDBString(String stepsFromDB) {

        ArrayList<String> stepsToDo = new ArrayList<String>();
        int firstWordPart = 0;
        int secondWordPart = 1;

        for (int index = 0; index < stepsFromDB.length(); index++) {

            if (index == (stepsFromDB.length() - 1) && stepsFromDB.charAt(index) != '$') {
                String bla = stepsFromDB.substring(firstWordPart, secondWordPart);
                if (bla.length() != 0) {
                    stepsToDo.add(bla);
                }
            }

            if (stepsFromDB.charAt(index) != '$') {
                secondWordPart++;
            } else {
                String bla = stepsFromDB.substring(firstWordPart, secondWordPart - 1);
                if (bla.length() != 0) {
                    stepsToDo.add(bla);
                }
                firstWordPart = secondWordPart;
                secondWordPart++;
            }
        }

        return stepsToDo;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_panne_beheben_anleitung, menu);
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
