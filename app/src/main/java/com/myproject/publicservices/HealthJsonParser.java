package com.myproject.publicservices;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vignesh on 08-06-2016.
 */
public class HealthJsonParser {

    public static String[] column1;
    public static String[] column2;
    public static String[] column3;
    public static String[] column4;
    public static String[] column5;
    public static String[] column6;
    public static String[] column7;
    public static String[] column8;

    public static final String JSON_ARRAY = "Result_Array";
    public static final String KEY_COLUMN1 = "id";
    public static final String KEY_COLUMN2 = "hospital_name";
    public static final String KEY_COLUMN3 = "doctor_name";
    public static final String KEY_COLUMN4 = "address";
    public static final String KEY_COLUMN5 = "specality";
    public static final String KEY_COLUMN6 = "functioning";
    public static final String KEY_COLUMN7 = "lattitude";
    public static final String KEY_COLUMN8 = "longitude";

    private JSONArray users = null;

    private String json;

    public HealthJsonParser(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            Log.v("Json",String.valueOf(users));

            column1 = new String[users.length()];
            column2 = new String[users.length()];
            column3 = new String[users.length()];
            column4 = new String[users.length()];
            column5 = new String[users.length()];
            column6 = new String[users.length()];
            column7 = new String[users.length()];
            column8 = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                column1[i] = jo.getString(KEY_COLUMN1);
                column2[i] = jo.getString(KEY_COLUMN2);
                column3[i] = jo.getString(KEY_COLUMN3);
                column4[i] = jo.getString(KEY_COLUMN4);
                column5[i] = jo.getString(KEY_COLUMN5);
                column6[i] = jo.getString(KEY_COLUMN6);
                column7[i] = jo.getString(KEY_COLUMN7);
                column8[i] = jo.getString(KEY_COLUMN8);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
