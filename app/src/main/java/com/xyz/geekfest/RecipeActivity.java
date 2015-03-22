package com.xyz.geekfest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeActivity extends ActionBarActivity {


    ArrayList<String> namelist = new ArrayList<String>();
    ArrayList<String> recipelist = new ArrayList<String>();
    ArrayList<String> youtubelist = new ArrayList<String>();
    ArrayList<String> imagelist = new ArrayList<String>();


    ArrayList<EachRow3> list3 = new ArrayList<RecipeActivity.EachRow3>();

    ListView mListView;

   MyAdapter3 q;
    EachRow3 each;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        String arrayString = getIntent().getStringExtra("data") ;

        mListView = (ListView)findViewById(R.id.recycler_view);

        q = new MyAdapter3(getApplicationContext(), 0, list3);
        q.setNotifyOnChange(true);

        String URL_NO_SEARCH="192.168.4.8:8000/api?list="+arrayString;
        Log.d("lol", URL_NO_SEARCH);

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                URL_NO_SEARCH, (String)null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d("LOL", "Response: " + response.toString());
                if (response != null) {
                    try {

                        JSONArray array=response.getJSONArray("data");


                        for(int i =0 ; i <namelist.size() ; i++)
                        {
                            each = new EachRow3();
                            each.cname = namelist.get(i) ;
//                            each.crecipe = pricelist.get(i) ;
                            // each.cpic  = ;
                            each.cyoutube = youtubelist.get(i);
                            each.crecipe = recipelist.get(i);
                            each.cimage = imagelist.get(i);


                            list3.add(each);
                        }




//                                itemid=array.getJSONObject(0).getString("ndbno");
//                                Log.d("lol",itemid);
//                                parseNutrients();
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



        mListView.setAdapter(q);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe, menu);
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



    class MyAdapter3 extends ArrayAdapter<EachRow3> {
        LayoutInflater inflat;
        ViewHolder holder;

        public MyAdapter3(Context context, int textViewResourceId,
                          ArrayList<EachRow3> objects) {
            super(context, textViewResourceId, objects);
            // TODO Auto-generated constructor stub
            inflat = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final int pos=position;

            if (convertView == null) {
                convertView = inflat.inflate(R.layout.card_list_item, null);
                holder = new ViewHolder();


                holder.textView = (TextView) convertView.findViewById(R.id.textview_name);
                holder.youtubeButton = (Button) convertView.findViewById(R.id.youtube);
                holder.recipeButton = (Button) convertView.findViewById(R.id.recipe);
                holder.imageView  = (ImageView) convertView.findViewById(R.id.image_name);

                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            EachRow3 row = getItem(position);
//            Log.d("size", row.text);

            holder.textView.setText(row.cname);
            holder.youtubeButton.setText("Watch recipe");
            holder.recipeButton.setText("View Recipe");
            holder.imageView.setImageResource(R.drawable.cook);

            // image
            // value

            return convertView;

        }




        private class ViewHolder {

            public TextView textView;
            public ImageView imageView;
            public Button youtubeButton;
            public Button recipeButton;

        }


        @Override
        public EachRow3 getItem(int position) {
            // TODO Auto-generated method stub
            return list3.get(position);
        }

    }




    private class EachRow3
    {
        String cname;
        String crecipe ;
        String cyoutube ;
        String cimage;

    }

}
