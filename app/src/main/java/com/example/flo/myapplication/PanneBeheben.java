package com.example.flo.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import java.util.ArrayList;


public class PanneBeheben extends ActionBarActivity {

    private FragmentTabHost mTabHost;
    private PanneDBHelper db;
    private PanneAdapter adapter;
    private ArrayList<Panne> pannen;
    private float lastX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panne_beheben);

        setupTabView();
        setupDB();
    }

    private void setupDB() {
        db = new PanneDBHelper(this);

        PannenCreator pannenCreator = new PannenCreator(this);
        pannenCreator.savePannenToDB(this);

    }

    private void setupTabView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);


        mTabHost.addTab(
                mTabHost.newTabSpec("tab0").setIndicator("Alle", null),
                PanneBehebenFragmentAllePannenTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Symptom", null),
                PanneBehebenFragmentSymptomTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Auto f\u00e4hrt", null),
                PanneBehebenFragmentFaehrtNochTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Auto f\u00e4hrt nicht", null),
                PanneBehebenFragmentFaehrtNichtTab.class, null);

        mTabHost.getTabWidget().getChildAt(3).getLayoutParams().width = 70;
        mTabHost.getTabWidget().getChildAt(2).getLayoutParams().width = 50;
        mTabHost.getTabWidget().getChildAt(1).getLayoutParams().width = 60;
        mTabHost.getTabWidget().getChildAt(0).getLayoutParams().width = 10;

    }

    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN: {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP: {
                float currentX = touchevent.getX();

                // if left to right swipe on screen
                if (lastX < currentX) {

                    switchTabs(false);
                }

                // if right to left swipe on screen
                if (lastX > currentX) {
                    switchTabs(true);
                }

                break;
            }
        }
        return false;
    }

    public void switchTabs(boolean direction) {
        if (direction) // true = move left
        {
            if (mTabHost.getCurrentTab() == 0) {
                mTabHost.setCurrentTab(mTabHost.getTabWidget().getTabCount() - 1);

            } else {
                mTabHost.setCurrentTab(mTabHost.getCurrentTab() - 1);
            }
        } else
        // move right
        {
            if (mTabHost.getCurrentTab() != (mTabHost.getTabWidget()
                    .getTabCount() - 1)) {
                mTabHost.setCurrentTab(mTabHost.getCurrentTab() + 1);
            } else {
                mTabHost.setCurrentTab(0);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.menu_panne_beheben, menu);
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
