package com.example.flo.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Flo on 23.06.2015.
 */
public class PanneAdapter extends ArrayAdapter<Panne> {

    private int layoutResourceId;
    private Context context;
    private ArrayList<Panne> panne_data;


    public PanneAdapter(Context context, int layoutResourceId, ArrayList<Panne> panne_data){
        super(context, layoutResourceId, panne_data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.panne_data = panne_data;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PanneHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PanneHolder();


            // #TODO !!! TEXTVIEWS AUSTAUSCHEN !!!
            holder.panneName = (TextView) row.findViewById(R.id.contactName);
            holder.panneUrsache = (TextView) row.findViewById(R.id.contactNumber);
            holder.panneSymptom = (TextView) row.findViewById(R.id.contactNumber);



            row.setTag(holder);
        } else {
            holder = (PanneHolder) row.getTag();
        }

        Panne panne = panne_data.get(position);
        // set text in row
        holder.panneName.setText(panne.getName());
        holder.panneSymptom.setText(panne.getSymptom());

        return row;
    }

    static class PanneHolder {
        TextView panneName;
        TextView panneUrsache;
        TextView panneSymptom;
    }
}
