package com.myproject.publicservices;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by vignesh on 05-06-2016.
 */
public class TransportFragmentAdapter extends ArrayAdapter<String> {

    private String[] ids;
    private String[] left;
    private String[] right;
    private Activity context;

    public TransportFragmentAdapter(Activity context, String[] ids, String[] column1, String[] column2) {
        super(context,R.layout.transport_fragment,ids);
        this.ids = ids;
        left = column1;
        right = column2;
        this.context = context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.transport_fragment_resource, null, true);
        TextView column1 = (TextView) listViewItem.findViewById(R.id.column1);
        TextView column2 = (TextView) listViewItem.findViewById(R.id.column2);
        column1.setText(left[position]);
        column2.setText(right[position]);

        return listViewItem;
    }

}
