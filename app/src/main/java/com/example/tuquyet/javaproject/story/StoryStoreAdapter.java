package com.example.tuquyet.javaproject.story;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuquyet.javaproject.R;

import java.util.List;

/**
 * Created by tuquyet on 29/04/2017.
 */

public class StoryStoreAdapter extends RecyclerView.Adapter<StoryStoreAdapter.ViewHolder> {
    private List<StoryItem> storyItemsList;
    private Context context;

    public StoryStoreAdapter(List<StoryItem> mStoryItems, Context context) {
        this.storyItemsList = mStoryItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Tạo view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Resources res = context.getResources();
        int idStory = storyItemsList.get(position).getStID();
        String mDrawableName = "bia_" + idStory;
        int resID = res.getIdentifier(mDrawableName, "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resID);
        holder.imgBia.setImageDrawable(drawable);
        holder.txtStoryname.setText(storyItemsList.get(position).getmName());
        holder.txtDescription.setText(Html.fromHtml(storyItemsList.get(position).getmDescreption()));
        holder.cardViewStoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Gui ID, Descreption, Ten truyen qua Descreption de hien th
                Intent intent = new Intent(context, StoryDescription.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putExtra("ID", storyItemsList.get(position).getStID())
                        .putExtra("String", storyItemsList.get(position).getmDescreption())
                        .putExtra("StoryName", storyItemsList.get(position).getmName());
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return storyItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBia;
        private TextView txtStoryname;
        private TextView txtDescription;
        private CardView cardViewStoryItem;


        public ViewHolder(View itemView) {
            super(itemView);
            imgBia = (ImageView) itemView.findViewById(R.id.img_bia);
            txtStoryname = (TextView) itemView.findViewById(R.id.textview_storyname);
            txtDescription = (TextView) itemView.findViewById(R.id.textview_storydescription);
            cardViewStoryItem = (CardView) itemView.findViewById(R.id.cardview_story);



        }


    }
}
