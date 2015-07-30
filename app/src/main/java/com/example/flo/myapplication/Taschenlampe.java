package com.example.flo.myapplication;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class Taschenlampe extends ActionBarActivity {

    private static final String TAG = "Pannenhilfe App: ";
    ImageButton flashlightSwitch;

    private Camera camera;
    private boolean isFlashOn;
    private boolean hasFlash;
    Camera.Parameters params;
    MediaPlayer mp;
    TextView batteryLevelTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taschenlampe);

        flashlightSwitch = (ImageButton) findViewById(R.id.btnSwitch);
        // First check if device is supporting flashlight or not
        hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!hasFlash) {

            AlertDialog.Builder builder = new AlertDialog.Builder(Taschenlampe.this);
            builder.setTitle(("Kein Blitz vorhanden!"));
            builder.setMessage("Sorry, dieses Smartphone besitzt keinen Blitz, daher kann die Taschenlampenfunktion leider nicht verwendet werden.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }
        // get the camera
        getCamera();
        // displaying button image
        toggleButtonImage();

        //show battery level
        Intent batteryIntent = this.getApplicationContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int rawlevel = batteryIntent.getIntExtra("level", -1);
        double scale = batteryIntent.getIntExtra("scale", -1);
        double level = -1;
        if (rawlevel >= 0 && scale > 0) {
            level = rawlevel / scale;
        }

        level = level * 100;
        batteryLevelTv = (TextView) findViewById(R.id.taschenlampe_akku_status_text);
        batteryLevelTv.setText((int) level + "%");

        // receive battery level change
        registerReceiver(this.mBatInfoReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        // Switch button click event to toggle flash on/off
        flashlightSwitch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView flashlightStatusText = (TextView) findViewById(R.id.taschenlampe_status_text);
                if (isFlashOn) {
                    // turn off flash
                    turnOffFlash();
                    flashlightStatusText.setText(("Aus"));
                } else {
                    // turn on flash
                    turnOnFlash();
                    flashlightStatusText.setText(("An"));

                }
            }
        });
    }

    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                params = camera.getParameters();
            } catch (RuntimeException e) {

            }
        }
    }

    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }

            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;

            // changing button/switch image
            toggleButtonImage();
        }

    }

    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }

            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;

            // changing button/switch image
            toggleButtonImage();
        }
    }


    private void toggleButtonImage() {
        if (isFlashOn) {
            flashlightSwitch.setImageResource(R.drawable.flashlight_on_hdpi);
        } else {
            flashlightSwitch.setImageResource(R.drawable.flashlight_off_hdpi);
        }
    }

   /* @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    */

    @Override
    protected void onPause() {
        super.onPause();

        // on pause turn off the flash
        turnOffFlash();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();

// on starting the app get the camera params
        getCamera();
        // on resume turn on the flash
        //  if(hasFlash)
        //    turnOnFlash();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // on starting the app get the camera params
        getCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();


        // on stop release the camera
        if (camera != null) {
            camera.release();
            camera = null;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.menu.menu_flashlight, menu);
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


    BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        //  TextView batteryLevelTv = (TextView)findViewById(R.id.taschenlampe_akku_status_text);

        @Override
        public void onReceive(Context arg0, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            // do something...
            batteryLevelTv.setText((int) level + "%");

        }
    };

}
