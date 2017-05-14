package com.example.tuquyet.javaproject.chapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tuquyet.javaproject.R;

import java.util.ArrayList;

/**
 * Created by tuquyet on 02/05/2017.
 */

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder2> {
    private ArrayList<ChapterItem> chapterItemArrayList;
    private Context context;


    public ChapterAdapter(ArrayList<ChapterItem> mChapterItems, Context context) {
        this.chapterItemArrayList = mChapterItems;
        this.context = context;
    }

    @Override
    public ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chapter, parent, false);
        return new ViewHolder2(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder2 holder, final int position) {

        if (chapterItemArrayList.get(position).getResultTmp() == null) {
            // khong co kq thoa man
            holder.cardViewChapterItem.setBackgroundColor(Color.RED);
        }
        else if (chapterItemArrayList.get(position).getResultTmp().size() == 0) {
            // chua search
            holder.cardViewChapterItem.setBackgroundColor(Color.WHITE);

        }
        else {
            holder.cardViewChapterItem.setBackgroundColor(Color.GREEN);

        }
        holder.mChapterName.setText(chapterItemArrayList.get(position).getDeName());
        holder.cardViewChapterItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ChapterContent.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Chapter", chapterItemArrayList.get(position));
                context.startActivities(new Intent[]{intent});
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterItemArrayList.size();
    }


    public class ViewHolder2 extends RecyclerView.ViewHolder {
        private TextView mChapterName;

        private CardView cardViewChapterItem;

        public ViewHolder2(View itemView) {
            super(itemView);
            mChapterName = (TextView) itemView.findViewById(R.id.txt_chapter_name);
            cardViewChapterItem = (CardView) itemView.findViewById(R.id.cardview_chapter);
        }
    }


}
