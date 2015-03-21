package com.xyz.geekfest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.pkmmte.view.CircularImageView;
import com.xyz.geekfest.Helperclasses.ScrollTabHolderFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

/**
 * Created by naman on 14/12/14.
 */

public class MainFragment  extends ScrollTabHolderFragment implements AbsListView.OnScrollListener {

    private static final String ARG_POSITION = "position";

    private ListView mListView;
    private ArrayList<String> mListItems;
    SmoothProgressBar progressBar;
    String URL_NO_SEARCH;
    String item="butter";
    String itemno;
    String itemname;
    KenBurnsView kenBurnsView;

    ArrayList<String> namelist = new ArrayList<String>();
    ArrayList<String> pricelist = new ArrayList<String>();
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

        String ingData = null ;

        ingData =loadJSONFromAsset();

        JSONArray test = new JSONArray() ;
        JSONObject myObject =null;


        try {
            myObject = new JSONObject(ingData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            test = myObject.getJSONArray("mandi");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(mPosition==0)
        {
            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Agra"))
                    {
                        namelist.add(test.getJSONObject(i).getString("Commodity"));
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(mPosition==1)
        {
            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Gonda"))
                    {
                        namelist.add(test.getJSONObject(i).getString("Commodity"));
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(mPosition==2)
        {
            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Faizabad"))
                    {
                        namelist.add(test.getJSONObject(i).getString("Commodity"));
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(mPosition==3)
        {
            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Etawah"))
                    {
                        namelist.add(test.getJSONObject(i).getString("Commodity"));
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(mPosition==4)
        {
            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Badayoun"))
                    {
                        namelist.add(test.getJSONObject(i).getString("Commodity"));
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(mPosition==5)
        {
            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Khurja"))
                    {
                        namelist.add(test.getJSONObject(i).getString("Commodity"));
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(mPosition==6)
        {
            for ( int i = 0; i < test.length() ; i++)
            {
                try {
                    if (test.getJSONObject(i).getString("Market").equals("Firozabad"))
                    {
                        namelist.add(test.getJSONObject(i).getString("Commodity"));
                        pricelist.add(test.getJSONObject(i).getString("Modal_x0020_Price"));
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
            // each.cvalue = ;

            list3.add(each);
        }

        mListView.setAdapter(q);

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
            holder.ingPrice.setText(row.cprice);

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







}