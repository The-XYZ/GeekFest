package com.xyz.geekfest.Helperclasses;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyz.geekfest.R;

/**
 * Created by yogesh on 22/3/15.
 */
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

