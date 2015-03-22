package com.xyz.geekfest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by naman on 22/03/15.
 */
public class DetailFood extends ActionBarActivity {

    String URL_NO_SEARCH;
    String SEARCH;
    String item,price,itemid=null;
    String sugar,carbo,fat,energy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_food);
        item=getIntent().getExtras().getString("name");
        price=getIntent().getExtras().getString("price");

        URL_NO_SEARCH="http://api.nal.usda.gov/usda/ndb/search/?format=json&q="+item+"&sort=n&max=25&offset=0&api_key=ELqzBqg05z4iZKEj5uX8SnFo5mzIpWbYhAbDP3M9";
        Log.d("lol", URL_NO_SEARCH);

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                URL_NO_SEARCH, (String)null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d("LOL", "Response: " + response.toString());
                if (response != null) {
            try {
                JSONObject object =response.getJSONObject("list");
                JSONArray array=object.getJSONArray("item");
                itemid=array.getJSONObject(0).getString("ndbno");
                Log.d("lol",itemid);

               parseNutrients();
            }
            catch (JSONException e){
            }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("LOL", "Error: " + error.getMessage());

            }
        });
        AppController.getInstance().addToRequestQueue(jsonReq);
    }

    private void parseNutrients(){

        SEARCH="http://api.nal.usda.gov/usda/ndb/nutrients/?format=json&api_key=ELqzBqg05z4iZKEj5uX8SnFo5mzIpWbYhAbDP3M9&nutrients=205&nutrients=204&nutrients=208&nutrients=269&ndbno="+itemid+"";
        Log.d("lol",SEARCH);
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                SEARCH, (String)null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d("LOL", "Response: " + response.toString());
                if (response != null) {
                    try {
                        JSONObject object =response.getJSONObject("report");
                        JSONArray array=object.getJSONArray("foods");
                        JSONObject object1 =array.getJSONObject(0);
                        JSONArray array1=object1.getJSONArray("nutrients");

                        sugar=array1.getJSONObject(0).getString("value");
                        carbo=array1.getJSONObject(2).getString("value");
                        fat=array1.getJSONObject(1).getString("value");
                        energy=array1.getJSONObject(3).getString("value");

                        Log.d("lol",sugar+carbo+fat+energy);


                    }
                    catch (JSONException e){
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("LOL", "Error: " + error.getMessage());

            }
        });
        AppController.getInstance().addToRequestQueue(jsonReq);

    }
}
