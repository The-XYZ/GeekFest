package com.xyz.geekfest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Budget extends ActionBarActivity {

    AutoCompleteTextView textView;
    ArrayAdapter<String> adapter;
    ArrayList<String> inglist = new ArrayList<String>();
    ArrayList<String> pricelist = new ArrayList<String>();
    String cost ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);


        List<String> responseList = new ArrayList<String>();

        JSONObject json = null;
        try {
            json = new JSONObject(loadJSONFromAsset());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray test =null;
        try {
             test=json.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < test.length(); i++) {
            try {
                responseList.add(test.getJSONObject(i).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

         adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, responseList);
         textView = (AutoCompleteTextView)
                findViewById(R.id.Months);
        textView.setAdapter(adapter);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                   JSONObject json = null;
                   try {
                       json = new JSONObject(loadJSONFromAsset());
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }

                   JSONArray test =null;
                   try {
                       test=json.getJSONArray("data");
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }

                if(position==0) {
                    try {
                        cost = test.getJSONObject(0).getString("cost");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JSONArray array2 =null;
                    try {
                         array2 = test.getJSONObject(0).getJSONArray("ing");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < array2.length(); i++) {
                        try {
                            inglist.add(array2.getJSONObject(i).getString("name"));
                            pricelist.add(array2.getJSONObject(i).getString("price"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if(position==1) {
                    try {
                        cost = test.getJSONObject(1).getString("cost");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JSONArray array2 =null;
                    try {
                        array2 = test.getJSONObject(1).getJSONArray("ing");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < array2.length(); i++) {
                        try {
                            inglist.add(array2.getJSONObject(i).getString("name"));
                            pricelist.add(array2.getJSONObject(i).getString("price"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if(position==2) {
                    try {
                        cost = test.getJSONObject(2).getString("cost");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JSONArray array2 =null;
                    try {
                        array2 = test.getJSONObject(2).getJSONArray("ing");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < array2.length(); i++) {
                        try {
                            inglist.add(array2.getJSONObject(i).getString("name"));
                            pricelist.add(array2.getJSONObject(i).getString("price"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if(position==3) {
                    try {
                        cost = test.getJSONObject(3).getString("cost");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JSONArray array2 =null;
                    try {
                        array2 = test.getJSONObject(3).getJSONArray("ing");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < array2.length(); i++) {
                        try {
                            inglist.add(array2.getJSONObject(i).getString("name"));
                            pricelist.add(array2.getJSONObject(i).getString("price"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }



            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public String loadJSONFromAsset() {

        String json = null;
        try {

            InputStream is = getAssets().open("dish.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }


}
