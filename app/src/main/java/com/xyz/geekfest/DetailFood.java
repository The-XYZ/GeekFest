package com.xyz.geekfest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.PercentFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by naman on 22/03/15.
 */
public class DetailFood extends ActionBarActivity {

    String URL_NO_SEARCH;
    String SEARCH;
    String item,price,price2,itemid=null,test,test2;
    String sugar,carbo,fat,energy;
    Integer integer,integer2;


    private PieChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_food);
        item=getIntent().getExtras().getString("name");
        price=getIntent().getExtras().getString("price");
        test=price.substring(price.length()-4,price.length());
        test2=price.replace(test,"");

       // integer=Integer.parseInt(test2);
        //integer2=integer/100;
        //price2=integer2.toString();

        getSupportActionBar().setTitle("Graph");

        mChart = (PieChart) findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);



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
                        // change the color of the center-hole
                        // mChart.setHoleColor(Color.rgb(235, 235, 235));
                        mChart.setHoleColorTransparent(true);

                        // mChart.setTouchEnabled(false);

                        mChart.setCenterText("₹\n"+test2);
                        mChart.setCenterTextSizePixels(150);

                        setData(3, 100);

                        mChart.animateXY(1500, 1500);


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
    private void setData(int count, float range) {
        Log.d("lol",carbo)
;        float mult = range;
        float carbof=Float.parseFloat(carbo);

        float sugarf;
        if (!sugar.equals("--"))
            sugarf=Float.parseFloat(sugar);
        else sugarf=Float.parseFloat("0.01");

        float fatf;
        if (!fat.equals("0.00"))
                fatf=Float.parseFloat(fat);
        else fatf=Float.parseFloat("0.01");
        float energyf=Float.parseFloat(energy);



        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

            yVals1.add(new Entry((float) carbof, 0));
        yVals1.add(new Entry((float) fatf, 1));
        yVals1.add(new Entry((float) sugarf, 2));
        yVals1.add(new Entry((float) energyf, 3));


        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count + 1; i++)
            xVals.add(mParties[i % mParties.length]);

       PieDataSet dataSet = new PieDataSet(yVals1, "Nutrients");
        dataSet.setSliceSpace(3f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }
    protected String[] mParties = new String[] {
            "Carbohydrates", "Fat", "Sugar", "Energy", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };
}
