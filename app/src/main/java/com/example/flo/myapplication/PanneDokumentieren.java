package com.example.flo.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PanneDokumentieren extends ActionBarActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private Gallery gallery;
    private ImageAdapter adapter;
    private ImageView currentBigImage;
    private String[] imageIDs;
    private String currentImagePath;

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    // hier zum befuellen des arrays methode benutzen, die in ordner nach vorhandenen photos sucht
   /* Integer[] imageIDs = {
            R.drawable.ic_gefahrenstelle1,
            R.drawable.ic_gefahrenstelle2,
            R.drawable.ic_camera1,
            R.drawable.ic_camera2,
            R.drawable.ic_kontakte1,
            R.drawable.ic_kontakte2,
    };

    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panne_dokumentieren);


        checkIfCameraIsAvailable();

        getImagesFromStorage();

        setupUI();
        setupGallery();

        showFirstPicture();

    }

    private void showFirstPicture() {
       // Log.w("bla", imageIDs[0]);
        if(imageIDs != null){
            Bitmap myBitmap;

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            options.outHeight = 50;
            options.outWidth = 50;
            options.inSampleSize = 4;

            for(int i = 0; i<imageIDs.length; i++){
                if(imageIDs[i] != null){
                    File imgFile = new File(imageIDs[0]);
                    myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);

                    currentBigImage.setImageBitmap(myBitmap);
                    currentImagePath = imageIDs[0];

                    break;
                }
            }


        }
    }

    private void getImagesFromStorage() {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Pannenhilfe");

        File[] test = mediaStorageDir.listFiles();

        imageIDs = new String[test.length];
        for(int i = 0; i<test.length; i++){
            Log.w("BLAAAAAAAAAAAA",test[i].getName());
            imageIDs[i]= test[i].getPath();
        }
    }

    private void setupGallery() {
        // Note that Gallery view is deprecated in Android 4.1---
         gallery = (Gallery) findViewById(R.id.vorhandene_photos_gallery);
        adapter = new ImageAdapter(this);
        gallery.setAdapter(adapter);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Bitmap myBitmap;

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPurgeable = true;
                options.outHeight = 50;
                options.outWidth = 50;
                options.inSampleSize = 4;

                File imgFile = new File(imageIDs[position]);
                myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);

                currentBigImage.setImageBitmap(myBitmap);
                currentImagePath = imageIDs[position];
            }
        });


        gallery.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Log.w("//////////////gallery ", "long clicke!!!");


                AlertDialog.Builder builder = new AlertDialog.Builder(PanneDokumentieren.this);
                builder.setTitle(("Bild loeschen?"));
                builder.setMessage(" ");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button

                        File imgFile = new File(imageIDs[position]);
                        boolean deleted = imgFile.delete();

                        getImagesFromStorage();
                        adapter.notifyDataSetChanged();



                    }
                });


                builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();



                return false;
            }
        });

    }


    private void setupUI() {

        currentBigImage = (ImageView) findViewById(R.id.aktuelles_photo_panne_dokumentieren);
        Button takeNewPictureButton = (Button)findViewById(R.id.neues_photo_button);
        takeNewPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create Intent to take a picture and return control to the calling application
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

                // start the image capture Intent
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

        currentBigImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("%%%%%%%% current image", "just cliiiiiicked");
            }
        });

        currentBigImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Log.w("%%%%%%%% current image", "loing cliiiiiicked");

                return false;
            }
        });

        final GestureDetector detector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            //new myGestureClass());

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // TODO Auto-generated method stub
                System.out.println("SINGLE TAP");
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("DOUBLE TAP");
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("DOUBLE TAP EVENT");

                zoomImage();

                return false;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent arg0) {
                // TODO Auto-generated method stub
                return false;
            }

        });

        View.OnTouchListener gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("TOUCH!" +event);
                if (detector.onTouchEvent(event)) {
                    return true;
                }
                return false;
            }
        };

        currentBigImage.setOnTouchListener(gestureListener);


    }

    private void zoomImage( ) {
        Intent intent = new Intent(PanneDokumentieren.this, PanneDokumentierenFullscreenBild.class);
        String image = currentImagePath;
        intent.putExtra("IMAGE", image);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.w("$$$$$$$$$$$$$", "on activity result");
        // Check which request we're responding to
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            // Make sure the request was successful
            Log.w("$$$$$$$$$$$$" , "on activity result2");


            if (resultCode == RESULT_OK) {
                Log.w("$$$$$$$$$$$$$" , "on activity result3");

                getImagesFromStorage();
                adapter.notifyDataSetChanged();

            }
        }
    }

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Pannenhilfe");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


    private void checkIfCameraIsAvailable() {

        boolean hasCamera = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA);

        if (!hasCamera) {

            AlertDialog.Builder builder = new AlertDialog.Builder(PanneDokumentieren.this);
            builder.setTitle(("Keine Kamera vorhanden!"));
            builder.setMessage("Sorry, dieses Smartphone besitzt keine Kamera, daher kann diese Funktion leider nicht verwendet werden.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
     //   getMenuInflater().inflate(R.menu.menu_panne_dokumentieren, menu);
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

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d(DEBUG_TAG,"onSingleTapConfirmed: " + e.toString());

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(DEBUG_TAG,"onDoubleTap: " + e.toString());

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d(DEBUG_TAG,"onDoubleTapEvent: " + e.toString());

        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(DEBUG_TAG,"onDown: " + e.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(DEBUG_TAG,"onShowPress: " + e.toString());

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(DEBUG_TAG,"onSingleTapUp: " + e.toString());

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(DEBUG_TAG,"onScroll: " + e1.toString() + "/" + e2.toString());

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(DEBUG_TAG,"onLongPress: " + e.toString());

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(DEBUG_TAG,"onFling: " + e1.toString() + "/" + e2.toString());

        return false;
    }


    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private int itemBackground;
        public ImageAdapter(Context c)
        {
            context = c;
            // sets a grey background; wraps around the images
            TypedArray a =obtainStyledAttributes(R.styleable.MyGallery);
            itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
            a.recycle();
        }
        // returns the number of images
        public int getCount() {
            return imageIDs.length;
        }
        // returns the ID of an item
        public Object getItem(int position) {
            return position;
        }
        // returns the ID of an item
        public long getItemId(int position) {
            return position;
        }
        // returns an ImageView view
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);

            Bitmap myBitmap;

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            options.outHeight = 50;
            options.outWidth = 50;
            options.inSampleSize = 4;

            File imgFile = new File(imageIDs[position]);
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);

            imageView.setImageBitmap(myBitmap);
            imageView.setLayoutParams(new Gallery.LayoutParams(100, 100));
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }

}
