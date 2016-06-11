package com.myproject.publicservices;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by vignesh on 08-06-2016.
 */
public class detailed_hospital_view extends Fragment {

    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public TextView textView4;
    public TextView textView5;
    private Button button;
    public static String hospital_name = "";
    public static String doctor_name = "";
    public static String address = "";
    public static String specality = "";
    public static String functioning = "";
    public static String lattitude = "";
    public static String longitude = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detailed_hospital_view,container,false);
        v.setBackgroundColor(getResources().getColor(android.R.color.white));
        textView1 = (TextView)v.findViewById(R.id.detailed_hospital_name);
        textView2 = (TextView)v.findViewById(R.id.detailed_hospital_doctor);
        textView3 = (TextView)v.findViewById(R.id.detailed_hospital_address);
        textView4 = (TextView)v.findViewById(R.id.detailed_hospital_specality);
        textView5 = (TextView)v.findViewById(R.id.detailed_hospital_functioning);
        textView1.setText(hospital_name);
        textView2.setText(doctor_name);
        textView3.setText(address);
        textView4.setText(specality);
        textView5.setText(functioning);
        button = (Button)v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUri = "http://maps.google.com/maps?q=loc:" + lattitude + "," + longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));

                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

                startActivity(intent);
            }
        });
        return v;
    }

}
