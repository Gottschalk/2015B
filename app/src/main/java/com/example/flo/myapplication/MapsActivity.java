package com.example.flo.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements LocationListener, OnMapReadyCallback {

    private GoogleMap googleMap;
    private Marker currentPosMarker;
    private SupportMapFragment supportMapFragment;
    private LocationManager locationManager;
    private Location location;

    //private static final String GOOGLE_API_KEY = "AIzaSyC1OuzCRRYU8FykKApdyiNIml5XxMweYm8";
    private static final String GOOGLE_API_KEY = "AIzaSyAegGx3EuWv0NyBrGQZ8JdRhCixQE9MGp0"; // Server key
    private EditText placeText;
    private double latitude = 0;
    private double longitude = 0;
    private int PROXIMITY_RADIUS = 50000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // setUpMapIfNeeded();

        setupUI();
        createMap();

    }

    private void setupUI() {
        placeText = (EditText) findViewById(R.id.mapsPlacesSearchEditText);
        Button searchPlacesButton = (Button) findViewById(R.id.mapsPlacesSearchButton);
        searchPlacesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPlaces();
            }
        });
    }

    private void createPlaces() {
        // akutelle position in lat und long speichern
        fetchCurrentLocation();

        String searchTag = placeText.getText().toString();
        placeText.setText("");
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&keyword=" + searchTag);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + GOOGLE_API_KEY);

        // funktionierender searchTag:  String searchString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=53.6020,53.6020&radius=50000&keyword=bank&sensor=false&key=AIzaSyDDEpWL-ajz5r0hVYTlS4DanzTdqDwEBQE";
        Log.e("#####searchtTAG####", googlePlacesUrl.toString());

        GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask();
        Object[] toPass = new Object[2];
        toPass[0] = googleMap;
        toPass[1] = googlePlacesUrl.toString();
        googlePlacesReadTask.execute(toPass);
    }

    private void fetchCurrentLocation() {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    private void createMap() {
        if (!isGooglePlayServicesAvailable()) {
            Log.w("####MAPS: ", "play services not available");
            finish();
        }
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.googleMap = map;
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!enabled) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
            builder.setMessage("GPS ist deaktiviert, aktivieren?")
                    .setCancelable(false)
                    .setPositiveButton("Gehe zu Einstellungen",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent callGPSSettingIntent = new Intent(
                                            android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivity(callGPSSettingIntent);
                                }
                            });
            builder.setNegativeButton("Abbrechen",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        }

        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            onLocationChanged(location);
        }
        locationManager.requestLocationUpdates(bestProvider, 10000, 0, this);

    }


    @Override
    public void onLocationChanged(Location location) {


        TextView locationTv = (TextView) findViewById(R.id.latlongLocation);
        fetchCurrentLocation();
        LatLng latLng = new LatLng(latitude, longitude);

        if (currentPosMarker == null) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Aktueller Standort");
            currentPosMarker = googleMap.addMarker(markerOptions);
        } else {
            currentPosMarker.setPosition(latLng);
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13));
        locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    public class GooglePlacesReadTask extends AsyncTask<Object, Integer, String> {
        String googlePlacesData = null;
        GoogleMap googleMap;

        @Override
        protected void onPreExecute() {
            findViewById(R.id.progressBarFetchPlaces).setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Object... inputObj) {
            try {
                googleMap = (GoogleMap) inputObj[0];
                String googlePlacesUrl = (String) inputObj[1];
                Http http = new Http();
                googlePlacesData = http.read(googlePlacesUrl);
            } catch (Exception e) {
                Log.e("Google Place Read Task", e.toString());
            }
            // Log.e("####PlacesREadTAsk: googlePlacesData", googlePlacesData);
            return googlePlacesData;
        }

        @Override
        protected void onPostExecute(String result) {
            PlacesDisplayTask placesDisplayTask = new PlacesDisplayTask();
            Object[] toPass = new Object[2];
            toPass[0] = googleMap;
            toPass[1] = result;
            placesDisplayTask.execute(toPass);
            findViewById(R.id.progressBarFetchPlaces).setVisibility(View.GONE);
        }
    }

}
