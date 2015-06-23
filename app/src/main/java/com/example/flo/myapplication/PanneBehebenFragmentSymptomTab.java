package com.example.flo.myapplication;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class PanneBehebenFragmentSymptomTab extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout_symptom, container, false);
        //  TextView tv = (TextView) v.findViewById(R.id.text);
        // tv.setText("Symptom");
        ListView nameListView = (ListView) v.findViewById(R.id.panne_beheben_fragment_symptom_listview);


        String[] codeLearnChapters = new String[]{"Kontrolllampen leuchten", "Lenkung schwerfaellig",
                "Motor ruckelt aber startet nicht", "Rauch aus dem Motorblock", "Auspuff laut"};

        ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, codeLearnChapters);

        nameListView.setAdapter(codeLearnArrayAdapter);
        return v;
    }
}
