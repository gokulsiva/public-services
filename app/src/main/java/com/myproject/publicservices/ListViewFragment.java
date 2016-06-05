package com.myproject.publicservices;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by vignesh on 05-06-2016.
 */
public class ListViewFragment extends Fragment {

    public static String url = "";
    public static String info = "";
    ProgressDialog loading;
    private ListView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.listview_fragment,container,false);
        list = (ListView) v.findViewById(R.id.listview);
        TextView text = (TextView) v.findViewById(R.id.listview_info);
        text.setText(info);
        return v;
    }


    private void sendRequest(){
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(getActivity(), "Please connect to a NETWORK and try again", Toast.LENGTH_LONG).show();
                            loading.dismiss();

                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getActivity(), "AuthFailureError", Toast.LENGTH_LONG).show();
                            loading.dismiss();

                        } else if (error instanceof ServerError) {
                            Toast.makeText(getActivity(), "ServerError", Toast.LENGTH_LONG).show();
                            loading.dismiss();

                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getActivity(), "NetworkError", Toast.LENGTH_LONG).show();
                            loading.dismiss();

                        } else if (error instanceof ParseError) {
                            Toast.makeText(getActivity(), "Parsing error try after some time", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }

                        Fragment fragment = new HomeFragment();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.mainFrame, fragment);
                        ft.commit();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity(), new HurlStack());
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        loading.dismiss();
        ListViewAdapter adapter = new ListViewAdapter(getActivity(), ParseJSON.ids,ParseJSON.column1,ParseJSON.column2);
        list.setAdapter(adapter);
        //Collections.reverse((List<?>) listView);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sendRequest();
        loading = ProgressDialog.show(getActivity(),"Please wait.....","Fetching Data",false,false);
    }

}
