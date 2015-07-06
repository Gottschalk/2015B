package com.example.flo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.maps.model.LatLng;


public class StartSplashActivity extends Activity {

    private LocationManager locationManager;
    private Location location;
    private double latitude = 0;
    private double longitude = 0;

    private final int SPLASH_DISPLAY_LENGHT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_splash);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(StartSplashActivity.this, MenuActivity.class);
                mainIntent.putExtra("latitude", latitude);
                mainIntent.putExtra("longitude", longitude);
                StartSplashActivity.this.startActivity(mainIntent);
                StartSplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }


}
