package com.example.flo.myapplication;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Flo on 20.06.2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PanneBehebenFragmentNameTab extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout_name, container, false);
      //  TextView tv = (TextView) v.findViewById(R.id.text);
     //   tv.setText("Name");
        ListView nameListView = (ListView)v.findViewById(R.id.panne_beheben_fragment_name_listview);
        return v;
    }
}
