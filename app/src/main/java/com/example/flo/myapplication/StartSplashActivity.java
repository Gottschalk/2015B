package com.example.flo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class StartSplashActivity extends Activity {


    private final int SPLASH_DISPLAY_LENGHT = 3000;            //set your time here......

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(StartSplashActivity.this, MenuActivity.class);
                StartSplashActivity.this.startActivity(mainIntent);
                StartSplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }


}
