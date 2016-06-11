package com.myproject.publicservices;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vignesh on 04-06-2016.
 */
public class EnergyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.energy_fragment,container,false);
        v.setBackgroundColor(getResources().getColor(android.R.color.white));
        return v;
    }

}
