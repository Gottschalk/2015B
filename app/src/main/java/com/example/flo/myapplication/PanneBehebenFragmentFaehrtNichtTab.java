package com.example.flo.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class PanneBehebenFragmentFaehrtNichtTab extends Fragment {


    private PanneDBHelper db;
    private PanneAdapter adapter;
    private ArrayList<Panne> pannen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout_faehrtnicht, container, false);
       // TextView tv = (TextView) v.findViewById(R.id.text);
       // tv.setText("Bauteil");
        final ListView faehrtNichtListview = (ListView)v.findViewById(R.id.panne_beheben_fragment_faehrtnicht_listview);

        db = new PanneDBHelper(getActivity());

        // falls zu viele testelemente in der db sind
        //   db.deleteAllContacts();

        pannen = db.getAllPannen();
        int size = 0;
        for (Panne panne : pannen) {

            if(panne.getFaehrtNoch().equals("false")){
                size++;
            }
            Log.w("OKOKK;OKOL ", String.valueOf(size));
        }

        String[] faehrtNichtArray = new String[size];

        int index = 0;

        for (Panne panne : pannen) {

            if(panne.getFaehrtNoch().equals("false")){
                faehrtNichtArray[index] = panne.getName();
                index++;
            }

        }

        ArrayAdapter<String> bauteilAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, faehrtNichtArray);

        faehrtNichtListview.setAdapter(bauteilAdapter);

        faehrtNichtListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), PanneBehebenAnleitung.class);
                int clickedItemIndex = (int) faehrtNichtListview.getAdapter().getItemId(position);

                String panneName = pannen.get(position).getName();
                String panneSchritte = pannen.get(position).getSchritte();
                String panneSchritteBilder = pannen.get(position).getBilder();

                int symptomId = pannen.get(position).getId();

                i.putExtra("NAME", panneName);
                i.putExtra("SCHRITTE", panneSchritte);
                i.putExtra("BILDER", panneSchritteBilder);


                startActivity(i);
            }
        });
        return v;
    }
}
