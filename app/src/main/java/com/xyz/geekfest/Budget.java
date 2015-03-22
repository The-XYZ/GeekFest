package com.xyz.geekfest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;


public class Budget extends ActionBarActivity {

    AutoCompleteTextView textView;
    ArrayAdapter<String> adapter;
    ArrayList<String> inglist = new ArrayList<String>();
    ArrayList<String> pricelist = new ArrayList<String>();
    String cost ;


    private FeatureCoverFlow mCoverFlow;
    private CoverFlowAdapter mAdapter ;
    private FloatingActionButton createDream;
    private ArrayList<DreamEntity> mData = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        mAdapter = new CoverFlowAdapter(getApplicationContext());
        mCoverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
//        createDream =(FloatingActionButton)findViewById(R.id.fab);


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
                            Bitmap pic = givepic(array2.getJSONObject(i).getString("iname"));
                            mData.add(new DreamEntity(pic, array2.getJSONObject(i).getString("iname"), array2.getJSONObject(i).getString("cost") ));
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
                            Bitmap pic = givepic(array2.getJSONObject(i).getString("iname"));
                            mData.add(new DreamEntity(pic, array2.getJSONObject(i).getString("iname"), array2.getJSONObject(i).getString("cost") ));

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
                            Bitmap pic = givepic(array2.getJSONObject(i).getString("iname"));
                            mData.add(new DreamEntity(pic, array2.getJSONObject(i).getString("iname"), array2.getJSONObject(i).getString("cost") ));
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
                            Bitmap pic = givepic(array2.getJSONObject(i).getString("iname"));
                            mData.add(new DreamEntity(pic, array2.getJSONObject(i).getString("iname"), array2.getJSONObject(i).getString("cost") ));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }


                mAdapter.setData(mData);
                mCoverFlow.setAdapter(mAdapter);


            }
        });

//        mTitle = (TextSwitcher) v.findViewById(R.id.title);





        mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
//                mTitle.setText((mData.get(position).data));
            }

            @Override
            public void onScrolling() {
//                mTitle.setText("");
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



    public class CoverFlowAdapter extends BaseAdapter {

        private ArrayList<DreamEntity> mData = new ArrayList<>(0);
        private Context mContext;

        public CoverFlowAdapter(Context context) {
            mContext = context;
        }

        public void setData(ArrayList<DreamEntity> data) {
            mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int pos) {
            return mData.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View rowView = convertView;

            if (rowView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.item_coverflow, null);

                ViewHolder viewHolder = new ViewHolder();
                viewHolder.name = (TextView) rowView.findViewById(R.id.name);
                viewHolder.image = (ImageView) rowView.findViewById(R.id.image);
                viewHolder.price = (TextView) rowView.findViewById(R.id.price);

//                viewHolder.mood = (TextView) rowView.findViewById(R.id.mood);

                rowView.setTag(viewHolder);
            }



            ViewHolder holder = (ViewHolder) rowView.getTag();



            Bitmap bitmap = mData.get(position).pic;

            holder.image.setImageBitmap(bitmap);


            holder.price.setText(mData.get(position).price);
//        holder.mood.setText(mData.get(position).mood);
            holder.name.setText(mData.get(position).name);


            return rowView;
        }


        public class ViewHolder {
            public TextView price;
            public ImageView image;
            public TextView name;
        }
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
        if(name.equals("cottage cheese"))
        {
            return BitmapFactory.decodeResource(getResources(), R.drawable.cottage_cheese);

        }


            return BitmapFactory.decodeResource(getResources(), R.drawable.dal);

    }

}
