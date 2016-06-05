package com.myproject.publicservices;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by vignesh on 04-06-2016.
 */
public class TransportFragment extends Fragment {

    private ImageButton imageButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.transport_fragment,container,false);
        imageButton = (ImageButton)v.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListViewFragment();
                ListViewFragment.url = "http://gokulonlinedatabase.net16.net/publicservice/publicservice.php?Table=bus";
                ListViewFragment.info = "Bus Details";
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame,fragment);
                ft.commit();
            }
        });
        return v;
    }


}

