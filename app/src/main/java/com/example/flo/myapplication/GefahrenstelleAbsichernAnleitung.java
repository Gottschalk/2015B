package com.example.flo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;


public class GefahrenstelleAbsichernAnleitung extends ActionBarActivity {

    private ViewFlipper viewFlipper;
    private float lastX;
    private int currentStep = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gefahrenstelle_absichern__anleitung);

        Intent i = getIntent();
        final String ort = i.getStringExtra("Ort");


        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        final TextView currentStepTV = (TextView)findViewById(R.id.gefahrenstelle_absichern_stepTextview);
        Button nextStep = (Button)findViewById(R.id.gefahrenstelle_absichern_nextStepButton);
        Button previousStep = (Button)findViewById(R.id.gefahrenstelle_absichern_previousStepButton);

        previousStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewFlipper.getDisplayedChild() != 0){
                    // Next screen comes in from left.
                    viewFlipper.setInAnimation(getApplicationContext(), R.anim.slide_in_from_left);
                    // Current screen goes out from right.
                    viewFlipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_to_right);

                    // Display next screen.
                    viewFlipper.showNext();
                    currentStep -=1;
                    currentStepTV.setText(("Schritt " + currentStep + " / 9" ));
                }
            }
        });

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewFlipper.getDisplayedChild() != 1){
                    // Next screen comes in from right.
                    viewFlipper.setInAnimation(getApplicationContext(), R.anim.slide_in_from_right);
                    // Current screen goes out from left.
                    viewFlipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_to_left);

                    // Display previous screen.
                    viewFlipper.showPrevious();
                    currentStep +=1;
                    currentStepTV.setText(("Schritt " + currentStep + " / 9" ));
                }
            }
        });


    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float currentX = touchevent.getX();

                // Handling left to right screen swap.
                if (lastX < currentX) {

                    // If there aren't any other children, just break.
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;

                    // Next screen comes in from left.
                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_left);
                    // Current screen goes out from right.
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_right);

                    // Display next screen.
                    viewFlipper.showNext();
                }

                // Handling right to left screen swap.
                if (lastX > currentX) {

                    // If there is a child (to the left), kust break.
                    if (viewFlipper.getDisplayedChild() == 1)
                        break;

                    // Next screen comes in from right.
                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);
                    // Current screen goes out from left.
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);

                    // Display previous screen.
                    viewFlipper.showPrevious();
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_gefahrenstelle_absichern__anleitung, menu);
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
