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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;


public class Budget extends ActionBarActivity {

    private FeatureCoverFlow mCoverFlow;
    private CoverFlowAdapter mAdapter;
    private FloatingActionButton createDream;
    private ArrayList<DreamEntity> mData = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

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
