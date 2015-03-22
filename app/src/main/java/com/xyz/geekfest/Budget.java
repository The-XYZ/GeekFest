package com.xyz.geekfest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;


public class Budget extends ActionBarActivity {

    AutoCompleteTextView textView;
    ArrayAdapter<String> adapter;
    ArrayList<String> inglist = new ArrayList<String>();
    ArrayList<String> pricelist = new ArrayList<String>();
    String cost ;


    private FeatureCoverFlow mCoverFlow;
    private CoverFlowAdapter mAdapter;
    private FloatingActionButton createDream;
    private ArrayList<DreamEntity> mData = new ArrayList<>(0);

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
        mAdapter = new CoverFlowAdapter(Budget.this);
        mCoverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        createDream =(FloatingActionButton)findViewById(R.id.fab);
//        mTitle = (TextSwitcher) v.findViewById(R.id.title);

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

    public class DreamEntity {
        public String imageResId;
        public String date;
        public String data;
        public String mood;
        public String time;


        public DreamEntity (String imageResId, String date, String data, String mood, String time){
            this.imageResId = imageResId;
            this.date= date;
            this.data = data;
            this.mood = mood;
            this.time = time;
        }
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
                viewHolder.data = (TextView) rowView.findViewById(R.id.data);
                viewHolder.image = (ImageView) rowView.findViewById(R.id.image);
                viewHolder.date = (TextView) rowView.findViewById(R.id.date);
                viewHolder.time = (TextView) rowView.findViewById(R.id.time);
//                viewHolder.mood = (TextView) rowView.findViewById(R.id.mood);


                rowView.setTag(viewHolder);
            }



            ViewHolder holder = (ViewHolder) rowView.getTag();


            BitmapFactory.Options options = new BitmapFactory.Options();
            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;
            final Bitmap bitmap = BitmapFactory.decodeFile(Uri.parse(mData.get(position).imageResId).getPath(),
                    options);

            holder.image.setImageBitmap(bitmap);

            holder.date.setText(mData.get(position).date);
            holder.time.setText(mData.get(position).time);
//        holder.mood.setText(mData.get(position).mood);
            holder.data.setText(mData.get(position).data);


            return rowView;
        }


        public class ViewHolder {
            public TextView date;
            public ImageView image;
            public TextView time;
            public TextView mood ;
            public TextView data;
        }
    }

}
