package com.xyz.geekfest;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.pkmmte.view.CircularImageView;
import com.xyz.geekfest.Helperclasses.ScrollTabHolderFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

/**
 * Created by naman on 14/12/14.
 */

public class MainFragment  extends ScrollTabHolderFragment implements AbsListView.OnScrollListener {

    private static final String ARG_POSITION = "position";

    Button recipe ;

    private ListView mListView;
    private ArrayList<String> mListItems;
    SmoothProgressBar progressBar;
    String URL_NO_SEARCH;
    String item="butter";
    String itemno;
    String itemname;
    KenBurnsView kenBurnsView;
    android.os.Handler handler;

    ArrayList<String> namelist = new ArrayList<String>();
    ArrayList<String> pricelist = new ArrayList<String>();
    ArrayList<String> nvaluelist = new ArrayList<String>();

    ArrayList<EachRow3> list3 = new ArrayList<MainFragment.EachRow3>();


    private int mPosition;
    MyAdapter3 q;
    EachRow3 each;


    public static Fragment newInstance(int position) {
        MainFragment f = new MainFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }


    public String loadJSONFromAsset() {

        String json = null;
        try {

            InputStream is = getActivity().getAssets().open("mandi.json");

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments().getInt(ARG_POSITION);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, null);

        mListView = (ListView) v.findViewById(R.id.listView);
        progressBar=(SmoothProgressBar) ((MainActivity)getActivity()).findViewById(R.id.google_now);

        kenBurnsView=(KenBurnsView) ((MainActivity)getActivity()).findViewById(R.id.header_picture);

        View placeHolderView = inflater.inflate(R.layout.view_header_placeholder, mListView, false);
        mListView.addHeaderView(placeHolderView);

        q = new MyAdapter3(getActivity(), 0, list3);
        q.setNotifyOnChange(true);

        recipe = (Button)  v.findViewById(R.id.recipeButton);
        recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Openrecipe();

            }
        });

        String ingData = null ;

        ingData =loadJSONFromAsset();



        if(mPosition==0)
        {

            JSONObject myObject =null;

            try {
                myObject = new JSONObject(ingData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test2 = new JSONArray() ;
            try {
                test2 = myObject.getJSONArray("mandi");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test = sortJsonArrayPrice(test2);

            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Agra"))
                    {item=test.getJSONObject(i).getString("Commodity");
                        namelist.add(item);
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));
                        if(!test.getJSONObject(i).optString("nu").equals(""))
                            nvaluelist.add(test.getJSONObject(i).optString("nu"));
                        else
                            nvaluelist.add("23");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(mPosition==1)
        {


            JSONObject myObject =null;

            try {
                myObject = new JSONObject(ingData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test2 = new JSONArray() ;
            try {
                test2 = myObject.getJSONArray("mandi");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test = sortJsonArrayPrice(test2);

            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Gonda"))
                    {item=test.getJSONObject(i).getString("Commodity");
                        namelist.add(item);
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));
                        if(!test.getJSONObject(i).optString("nu").equals(""))
                            nvaluelist.add(test.getJSONObject(i).optString("nu"));
                        else
                            nvaluelist.add("23");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(mPosition==2)
        {


            JSONObject myObject =null;

            try {
                myObject = new JSONObject(ingData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test2 = new JSONArray() ;
            try {
                test2 = myObject.getJSONArray("mandi");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test = sortJsonArrayPrice(test2);


            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Faizabad"))
                    {item=test.getJSONObject(i).getString("Commodity");
                        namelist.add(item);
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));
                        if(!test.getJSONObject(i).optString("nu").equals(""))
                            nvaluelist.add(test.getJSONObject(i).optString("nu"));
                        else
                            nvaluelist.add("23");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(mPosition==3)
        {

            JSONObject myObject =null;

            try {
                myObject = new JSONObject(ingData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test2 = new JSONArray() ;
            try {
                test2 = myObject.getJSONArray("mandi");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test = sortJsonArrayPrice(test2);


            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Etawah"))
                    {item=test.getJSONObject(i).getString("Commodity");
                        namelist.add(item);
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));
                        if(!test.getJSONObject(i).optString("nu").equals(""))
                            nvaluelist.add(test.getJSONObject(i).optString("nu"));
                        else
                            nvaluelist.add("23");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(mPosition==4)
        {

            JSONObject myObject =null;

            try {
                myObject = new JSONObject(ingData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test2 = new JSONArray() ;
            try {
                test2 = myObject.getJSONArray("mandi");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test = sortJsonArrayPrice(test2);


            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Badayoun"))
                    {   item=test.getJSONObject(i).getString("Commodity");
                        namelist.add(item);
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));



                        if(!test.getJSONObject(i).optString("nu").equals(""))
                            nvaluelist.add(test.getJSONObject(i).optString("nu"));
                        else
                            nvaluelist.add("23");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(mPosition==5)
        {


            JSONObject myObject =null;

            try {
                myObject = new JSONObject(ingData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test2 = new JSONArray() ;
            try {
                test2 = myObject.getJSONArray("mandi");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test = sortJsonArrayPrice(test2);


            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Khurja"))
                    {item=test.getJSONObject(i).getString("Commodity");
                        namelist.add(item);
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));
                        if(!test.getJSONObject(i).optString("nu").equals(""))
                            nvaluelist.add(test.getJSONObject(i).optString("nu"));
                        else
                            nvaluelist.add("23");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(mPosition==6)
        {

            JSONObject myObject =null;

            try {
                myObject = new JSONObject(ingData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test2 = new JSONArray() ;
            try {
                test2 = myObject.getJSONArray("mandi");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray test = sortJsonArrayPrice(test2);


            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Firozabad"))
                    {item=test.getJSONObject(i).getString("Commodity");
                        namelist.add(item);
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));
                        if(!test.getJSONObject(i).optString("nu").equals(""))
                            nvaluelist.add(test.getJSONObject(i).optString("nu"));
                        else
                            nvaluelist.add("23");


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }


        for(int i =0 ; i <namelist.size() ; i++)
        {
            each = new EachRow3();
            each.cname = namelist.get(i) ;
            each.cprice = pricelist.get(i) ;
            // each.cpic  = ;
            each.cvalue = nvaluelist.get(i);

            list3.add(each);
        }

        mListView.setAdapter(q);


        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                progressBar.setVisibility(View.GONE);
            }
        }, 2000);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),DetailFood.class);
                intent.putExtra("name",namelist.get(position-1));
                intent.putExtra("price",pricelist.get(position-1));
                Log.d("lol",item);
                startActivity(intent);
            }
        });


        Openrecipe();

        return v;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView.setOnScrollListener(this);
       //  mListView.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.list_item, android.R.id.text1, mListItems));
    }

    @Override
    public void adjustScroll(int scrollHeight) {
        if (scrollHeight == 0 && mListView.getFirstVisiblePosition() >= 1) {
            return;
        }

        mListView.setSelectionFromTop(1, scrollHeight);

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mScrollTabHolder != null)
            mScrollTabHolder.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount, mPosition);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // nothing
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
                convertView = inflat.inflate(R.layout.list_item_ing, null);
                holder = new ViewHolder();


                holder.ingName = (TextView) convertView.findViewById(R.id.ing_name);
                holder.ingPrice = (TextView) convertView.findViewById(R.id.ing_price);
                holder.ingNV = (TextView) convertView.findViewById(R.id.ing_nv);
                holder.img  = (CircularImageView) convertView.findViewById(R.id.img);

                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            EachRow3 row = getItem(position);
//            Log.d("size", row.text);

            holder.ingName.setText(row.cname);
            holder.ingPrice.setText("₹"+row.cprice);
            holder.ingNV.setText(row.cvalue);

            // image
            // value

            return convertView;

        }




        private class ViewHolder {

            public TextView ingName;
            public TextView ingPrice;
            public TextView ingNV;
            public CircularImageView img;

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
        String cprice ;
        Drawable cimage ;
        String cvalue;

    }


    public void Openrecipe()
    {
        String joined = TextUtils.join(",", namelist);
       joined =  joined.replace("(","");
        joined =  joined.replace(")","");
        joined =  joined.replace(" ",",");
        joined =  joined.replace("-",",");

        Intent i = new Intent(getActivity() ,  RecipeActivity.class);
        i.putExtra("data" , joined);
        startActivity(i);


    }


    public static JSONArray sortJsonArrayPrice(JSONArray array) {
        List<JSONObject> jsons = new ArrayList<JSONObject>();
        for (int i = 0; i < array.length(); i++) {
            try {
                jsons.add(array.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(jsons, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject lhs, JSONObject rhs) {
                String lid = null;
                try {
                    lid = lhs.getString("Modal_x0020_Price");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String rid = null;
                try {
                    rid = rhs.getString("Modal_x0020_Price");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Here you could parse string id to integer and then compare.
                return lid.compareTo(rid);
            }
        });
        return new JSONArray(jsons);
    }

    public static JSONArray sortJsonArrayValue(JSONArray array) {
        List<JSONObject> jsons = new ArrayList<JSONObject>();
        for (int i = 0; i < array.length(); i++) {
            try {
                jsons.add(array.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(jsons, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject lhs, JSONObject rhs) {
                String lid = null;
                try {
                    lid = lhs.getString("vu");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String rid = null;
                try {
                    rid = rhs.getString("vu");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Here you could parse string id to integer and then compare.
                return lid.compareTo(rid);
            }
        });
        return new JSONArray(jsons);
    }


}