package com.xyz.geekfest;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

    RecyclerView mListView;

    MyCardsAdapter q;
    EachRow3 each;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        String arrayString = getIntent().getStringExtra("data") ;




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




        mListView = (RecyclerView) findViewById(R.id.recycler_view);

        q = new MyCardsAdapter( list3);




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




    public class MyCardsAdapter extends RecyclerView.Adapter<MyCardsAdapter.ViewHolder>{

        private String[] myList;

        public MyCardsAdapter(final String[] list){
            myList = list;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            CardView v = (CardView) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_list_item, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
            viewHolder.textView.setText(myList[i].toString());
            viewHolder.imageView.setImageResource(R.drawable.two);
            viewHolder.youtubeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return 376;
        }


        public long getItemId(final int position) {
            return position;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public TextView textView;
            public ImageView imageView;
            public Button youtubeButton;

            public ViewHolder(CardView itemView) {
                super(itemView);
                this.textView = (TextView) itemView.findViewById(R.id.textview_name);
                this.imageView = (ImageView) itemView.findViewById(R.id.image_name);
                this.youtubeButton = (Button) itemView.findViewById(R.id.youtube );
            }
        }
    }


    private class EachRow3
    {
        String cname;
        String crecipe ;
        Drawable cyoutube ;
        String cimage;

    }

}
