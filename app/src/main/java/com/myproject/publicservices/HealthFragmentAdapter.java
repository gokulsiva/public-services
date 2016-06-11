package com.myproject.publicservices;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by vignesh on 08-06-2016.
 */
public class HealthFragmentAdapter extends ArrayAdapter<String> {

    private Activity context;
    private String[] column2;
    private String[] column3;


    public HealthFragmentAdapter(Activity context, String[] column2, String[] column3) {
        super(context,R.layout.health_fragment,column2);
        this.context = context;
        this.column2 = column2;
        this.column3 = column3;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.health_fragment_resource, null, true);
        TextView column1 = (TextView) listViewItem.findViewById(R.id.hospital_name);
        TextView column2 = (TextView) listViewItem.findViewById(R.id.hospital_doctor);
        column1.setText(this.column2[position]);
        column2.setText(this.column3[position]);

        return listViewItem;
    }

}

