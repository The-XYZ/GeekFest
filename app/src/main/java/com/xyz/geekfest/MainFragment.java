package com.xyz.geekfest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xyz.geekfest.Helperclasses.ScrollTabHolderFragment;

import java.util.ArrayList;

/**
 * Created by naman on 14/12/14.
 */

public class MainFragment  extends ScrollTabHolderFragment implements AbsListView.OnScrollListener {

    private static final String ARG_POSITION = "position";

    private ListView mListView;
    private ArrayList<String> mListItems;

    private int mPosition;

    public static Fragment newInstance(int position) {
        MainFragment f = new MainFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments().getInt(ARG_POSITION);

        mListItems = new ArrayList<String>();

        for (int i = 1; i <= 100; i++) {
            mListItems.add(i + ". item - currnet page: " + (mPosition + 1));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, null);

        mListView = (ListView) v.findViewById(R.id.listView);

        View placeHolderView = inflater.inflate(R.layout.view_header_placeholder, mListView, false);
        mListView.addHeaderView(placeHolderView);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView.setOnScrollListener(this);
        mListView.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.list_item, android.R.id.text1, mListItems));
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

    private static class ViewHolder {
        public TextView ingName;
        public TextView ingPrice;
        public TextView ingNV;
        public ImageView img;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        ViewHolder holder = new ViewHolder();

        // First let's verify the convertView is not null
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item_ing, null);
            // Now we can fill the layout with the right values
            TextView INGname = (TextView) v.findViewById(R.id.ing_name);
            TextView INGprice = (TextView) v.findViewById(R.id.ing_price);
            TextView INGnv = (TextView) v.findViewById(R.id.ing_nv);
            ImageView img = (ImageView) v.findViewById(R.id.img);

            holder.ingName = INGname;
            holder.ingPrice = INGprice;
            holder.ingNV = INGnv;
            holder.img = img;

            v.setTag(holder);
        }
        else
            holder = (ViewHolder) v.getTag();

        return v;
    }

}