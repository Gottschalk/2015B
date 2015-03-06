package com.example.flo.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Flo_2 on 14.02.2015.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {
    Context context;
    int layoutResourceId;
   // Contact data[] = null;
    ArrayList<Contact> contact_data = null;

    public ContactAdapter(Context context, int layoutResourceId, ArrayList<Contact> contact_data) {
        super(context, layoutResourceId, contact_data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.contact_data = contact_data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ContactHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ContactHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
            holder.contactName = (TextView) row.findViewById(R.id.contactName);
            holder.contactNumber = (TextView) row.findViewById(R.id.contactNumber);


            row.setTag(holder);
        } else {
            holder = (ContactHolder) row.getTag();
        }

        Contact contact = contact_data.get(position);
        // Text in Listrow zuweisen
        holder.contactName.setText(contact.name);
        holder.contactNumber.setText(contact.number);

        holder.imgIcon.setImageResource(contact.icon);

        return row;
    }

    static class ContactHolder {
        ImageView imgIcon;
        TextView contactName;
        TextView contactNumber;
    }
}
