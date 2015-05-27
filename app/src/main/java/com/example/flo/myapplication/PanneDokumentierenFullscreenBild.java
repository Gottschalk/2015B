package com.example.flo.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;


public class PanneDokumentierenFullscreenBild extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panne_dokumentieren_fullscreen_bild);

        ImageView detailImage = (ImageView)findViewById(R.id.panne_dokumentieren_detailbild);
        Intent i = getIntent();
        String imagePath = i.getStringExtra("IMAGE");
        Bitmap myBitmap;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.outHeight = 50;
        options.outWidth = 50;
        options.inSampleSize = 4;

        File imgFile = new File(imagePath);
        myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);

        detailImage.setImageBitmap(myBitmap);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_panne_dokumentieren_fullscreen_bild, menu);
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
