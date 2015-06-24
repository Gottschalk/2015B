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


public class PanneBehebenFragmentSymptomTab extends Fragment {

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
        View v = inflater.inflate(R.layout.fragment_layout_symptom, container, false);
        //  TextView tv = (TextView) v.findViewById(R.id.text);
        // tv.setText("Symptom");
        final ListView symptomListView = (ListView) v.findViewById(R.id.panne_beheben_fragment_symptom_listview);


        db = new PanneDBHelper(getActivity());

        // falls zu viele testelemente in der db sind
        //   db.deleteAllContacts();

        pannen = db.getAllPannen();

        String[] symptomArray = new String[pannen.size()];

        int index = 0;

        for (Panne panne : pannen) {
          //   String log = "Id: " + panne.getId() + " ,Name: " + panne.getName() + " ,Ursache: " + panne.getBauteil() + "Symptom" + panne.getSymptom();
          //  Log.e("SymptomFragment: ", log + "  / index: " + index);
          //      Log.e("SymptomFragment: " , panne.getSymptom());

            symptomArray[index] = panne.getSymptom();
            index++;
        }

        ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, symptomArray);

        symptomListView.setAdapter(codeLearnArrayAdapter);

        symptomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity() , PanneBehebenAnleitung.class);
                int clickedItemIndex = (int) symptomListView.getAdapter().getItemId(position);

                String panneName = pannen.get(position).getName();


                int symptomId = pannen.get(position).getId();

                Log.w("###############nr:", String.valueOf(symptomId));

                i.putExtra("NAME", panneName);

                startActivity(i);
            }
        });
        return v;
    }
}
