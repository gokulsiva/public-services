package com.myproject.publicservices;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vignesh on 04-06-2016.
 */
public class HealthFragment extends Fragment {

    public static String url = "";
    public static String health_info = "";
    ProgressDialog loading;
    private ListView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.health_fragment,container,false);
        v.setBackgroundColor(getResources().getColor(android.R.color.white));
        list = (ListView) v.findViewById(R.id.healthView);
        TextView text = (TextView) v.findViewById(R.id.health_info);
        text.setText(health_info);
        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                detailed_hospital_view.hospital_name = HealthJsonParser.column2[position];
                detailed_hospital_view.doctor_name = HealthJsonParser.column3[position];
                detailed_hospital_view.address = HealthJsonParser.column4[position];
                detailed_hospital_view.specality = HealthJsonParser.column5[position];
                detailed_hospital_view.functioning = HealthJsonParser.column6[position];
                detailed_hospital_view.lattitude = HealthJsonParser.column7[position];
                detailed_hospital_view.longitude = HealthJsonParser.column8[position];
                Fragment fragment = new detailed_hospital_view();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        return v;
    }

    private void showJSON(String json){
        HealthJsonParser pj = new HealthJsonParser(json);
        pj.parseJSON();
        loading.dismiss();
        HealthFragmentAdapter adapter = new HealthFragmentAdapter(getActivity(), HealthJsonParser.column2, HealthJsonParser.column3);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loading = ProgressDialog.show(getActivity(),"Please wait.....","Fetching Data",false,false);
        GetJson getJson = new GetJson(getActivity(),url);
        getJson.jsonRequest(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                Set<String> errors = new HashSet<String>();
                errors.add(getResources().getString(R.string.Timeout_Error));
                errors.add(getResources().getString(R.string.Auth_Error));
                errors.add(getResources().getString(R.string.Server_Error));
                errors.add(getResources().getString(R.string.Network_Error));
                errors.add(getResources().getString(R.string.Parse_Error));


                if(errors.contains(result)){

                    Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
                    loading.dismiss();
                    Fragment fragment = new HomeFragment();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.mainFrame, fragment);
                    ft.commit();
                }else {
                    Log.v("Result:",result);
                    showJSON(result);
                }
            }
        });

    }
}
