package com.myproject.publicservices;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vignesh on 11-06-2016.
 */
public class RegularWaterJsonParser {
    public static String[] ids;
    public static String[] column1;
    public static String[] column2;

    public static final String JSON_ARRAY = "Result_Array";
    public static final String KEY_ID = "Id";
    public static final String KEY_COLUMN1 = "Name";
    public static final String KEY_COLUMN2 = "Time";

    private JSONArray users = null;

    private String json;

    public RegularWaterJsonParser(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            Log.v("Json",String.valueOf(users));

            ids = new String[users.length()];
            column1 = new String[users.length()];
            column2 = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                column1[i] = jo.getString(KEY_COLUMN1);
                column2[i] = jo.getString(KEY_COLUMN2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
