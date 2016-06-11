package com.myproject.publicservices;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by vignesh on 04-06-2016.
 */
public class WaterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.water_fragment,container,false);
        v.setBackgroundColor(getResources().getColor(android.R.color.white));
        Button button2 = (Button)v.findViewById(R.id.button2);
        Button button3 = (Button)v.findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Water24x7.water24x7_info = "24x7 WATER RESERVOIRS";
                Water24x7.url = "http://gokulonlinedatabase.net16.net/publicservice/publicservice.php?Table=24x7_water";
                Fragment fragment = new Water24x7();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, fragment);
                ft.commit();

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegularWater.info = "Today Water Schedule";
                RegularWater.url = "http://gokulonlinedatabase.net16.net/publicservice/publicservice.php?Table=dailyWater";
                Fragment fragment = new RegularWater();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, fragment);
                ft.commit();

            }
        });
        return v;

    }

}
