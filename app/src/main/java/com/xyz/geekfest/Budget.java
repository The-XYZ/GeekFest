package com.xyz.geekfest;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.getbase.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;


public class Budget extends ActionBarActivity {


    ArrayList<String> namelist = new ArrayList<String>();
    ArrayList<String> costlist = new ArrayList<String>();
    ArrayList<Bitmap> piclist = new ArrayList<Bitmap>();


    ArrayList<EachRow3> list3 = new ArrayList<Budget.EachRow3>();


    MyAdapter3 q;
    EachRow3 each;
    ListView mListView;

    AutoCompleteTextView textView;
    ArrayAdapter<String> adapter;
    ArrayList<String> inglist = new ArrayList<String>();
    ArrayList<String> pricelist = new ArrayList<String>();
    String cost ;


//    private FeatureCoverFlow mCoverFlow;
//    private CoverFlowAdapter mAdapter ;
    private FloatingActionButton createDream;
    private ArrayList<DreamEntity> mData = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);



        mListView = (ListView)findViewById(R.id.recycler_view);
        getSupportActionBar().setElevation(0);

        q = new MyAdapter3(getApplicationContext(), 0, list3);
        q.setNotifyOnChange(true);


        createDream = (FloatingActionButton)findViewById(R.id.fab);


//        mAdapter = new CoverFlowAdapter(getApplicationContext());
//        mCoverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
////        createDream =(FloatingActionButton)findViewById(R.id.fab);
//

        List<String> responseList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, responseList);

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


         textView = (AutoCompleteTextView)
                findViewById(R.id.Months);
        textView.setThreshold(1);
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

                if(adapter.getItem(position).toString().equals("Dal Makhani chawal")) {
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
                            Bitmap pic = givepic(array2.getJSONObject(i).getString("iname"));
                            namelist.add(array2.getJSONObject(i).getString("iname"));
                            costlist.add(array2.getJSONObject(i).getString("cost"));
                            piclist.add(pic);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if(adapter.getItem(position).toString().equals("Dum Aloo + roti")) {
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
                            Bitmap pic = givepic(array2.getJSONObject(i).getString("iname"));
                            namelist.add(array2.getJSONObject(i).getString("iname"));
                            costlist.add(array2.getJSONObject(i).getString("cost"));
                            piclist.add(pic);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if(adapter.getItem(position).toString().equals("Aloo Capcicum + roti")) {
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
                         Bitmap pic = givepic(array2.getJSONObject(i).getString("iname"));
                            namelist.add(array2.getJSONObject(i).getString("iname"));
                            costlist.add(array2.getJSONObject(i).getString("cost"));
                            piclist.add(pic);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if(adapter.getItem(position).toString().equals("Palak Paneer + roti")) {
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
                            Bitmap pic = givepic(array2.getJSONObject(i).getString("iname"));
                            namelist.add(array2.getJSONObject(i).getString("iname"));
                            costlist.add(array2.getJSONObject(i).getString("cost"));
                            piclist.add(pic);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                for(int i =0 ; i <namelist.size() ; i++)
                {
                    each = new EachRow3();
                    each.name = namelist.get(i) ;
//                            each.crecipe = pricelist.get(i) ;
                    // each.cpic  = ;
                    each.price = costlist.get(i);
                    each.pic = piclist.get(i);



                    list3.add(each);
                }


                mListView.setAdapter(q);


//                mAdapter.setData(mData);
//                mCoverFlow.setAdapter(mAdapter);


            }
        });

//        mTitle = (TextSwitcher) v.findViewById(R.id.title);





//        mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
//            @Override
//            public void onScrolledToPosition(int position) {
////                mTitle.setText((mData.get(position).data));
//            }
//
//            @Override
//            public void onScrolling() {
////                mTitle.setText("");
//            }
//        });


        createDream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Estimated call is :"+ cost, Toast.LENGTH_LONG).show();
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



    public Bitmap givepic(String name)
    {
        if(name.equals("dal"))
        {
            return BitmapFactory.decodeResource(getResources(), R.drawable.dal);

        }
        if(name.equals("oil"))
        {
            return BitmapFactory.decodeResource(getResources(), R.drawable.oil);

        }
        if(name.equals("tomato"))
        {
            return BitmapFactory.decodeResource(getResources(), R.drawable.tomato);

        }
        if(name.equals("potato"))
        {
            return BitmapFactory.decodeResource(getResources(), R.drawable.potatoes);

        }
        if(name.equals("rice"))
        {
            return BitmapFactory.decodeResource(getResources(), R.drawable.rice);

        }
        if(name.equals("onion"))
        {
            return BitmapFactory.decodeResource(getResources(), R.drawable.onion);

        }
        if(name.equals("wheat"))
        {
            return BitmapFactory.decodeResource(getResources(), R.drawable.wheat);

        }
        if(name.equals("carrot"))
        {
            return BitmapFactory.decodeResource(getResources(), R.drawable.carrot);

        }
        if(name.equals("spinach"))
        {
            return BitmapFactory.decodeResource(getResources(), R.drawable.carrot);

        }
        if(name.equals("cottage cheese"))
        {
            return BitmapFactory.decodeResource(getResources(), R.drawable.cottage_cheese);

        }

            return BitmapFactory.decodeResource(getResources(), R.drawable.dal);

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
                convertView = inflat.inflate(R.layout.card_list_item2, null);
                holder = new ViewHolder();


                holder.textViewname = (TextView) convertView.findViewById(R.id.textview_name);
                holder.textViewprice = (TextView) convertView.findViewById(R.id.textview_price);

                holder.imageView  = (ImageView) convertView.findViewById(R.id.image_name);

                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            EachRow3 row = getItem(position);
//            Log.d("size", row.text);


            holder.imageView.setImageBitmap(row.pic);

            holder.textViewname.setText(row.name);
            holder.textViewprice.setText(row.price);

//
//            holder.imageView.setImageUrl(row.cimage,imageLoader);

            // image
            // value

            return convertView;

        }




        private class ViewHolder {

            public TextView textViewname;
            public TextView textViewprice;

            public ImageView imageView;


        }


        @Override
        public EachRow3 getItem(int position) {
            // TODO Auto-generated method stub
            return list3.get(position);
        }

    }




    private class EachRow3
    {
        public String name;
        public String price;
        public Bitmap pic;
    }


}
