package com.myproject.publicservices;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vignesh on 05-06-2016.
 */
public class TransportFragment extends Fragment {

    public static String url = "";
    public static String info = "";
    ProgressDialog loading;
    private ListView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.transport_fragment,container,false);
        v.setBackgroundColor(getResources().getColor(android.R.color.white));
        list = (ListView) v.findViewById(R.id.listview);
        TextView text = (TextView) v.findViewById(R.id.listview_info);
        text.setText(info);
        return v;
    }



    private void showJSON(String json){
        TransportJsonParser pj = new TransportJsonParser(json);
        pj.parseJSON();
        loading.dismiss();
        TransportFragmentAdapter adapter = new TransportFragmentAdapter(getActivity(), TransportJsonParser.ids, TransportJsonParser.column1, TransportJsonParser.column2);
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
