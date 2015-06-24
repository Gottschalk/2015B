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

/**
 * Created by Flo on 20.06.2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PanneBehebenFragmentNameTab extends Fragment {


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
        View v = inflater.inflate(R.layout.fragment_layout_name, container, false);
      //  TextView tv = (TextView) v.findViewById(R.id.text);
     //   tv.setText("Name");
        final ListView nameListView = (ListView)v.findViewById(R.id.panne_beheben_fragment_name_listview);
        db = new PanneDBHelper(getActivity());

        // falls zu viele testelemente in der db sind
        //   db.deleteAllContacts();

        pannen = db.getAllPannen();

        String[] nameArray = new String[pannen.size()];

        int index = 0;

        for (Panne panne : pannen) {
            //   String log = "Id: " + panne.getId() + " ,Name: " + panne.getName() + " ,Ursache: " + panne.getBauteil() + "Symptom" + panne.getSymptom();
            //  Log.e("SymptomFragment: ", log + "  / index: " + index);
            //      Log.e("SymptomFragment: " , panne.getSymptom());

            nameArray[index] = panne.getName();
            index++;
        }

        ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, nameArray);

        nameListView.setAdapter(nameAdapter);

        nameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity() , PanneBehebenAnleitung.class);
                int clickedItemIndex = (int) nameListView.getAdapter().getItemId(position);

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
