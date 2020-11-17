package com.fmohammadi.instagramjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fmohammadi.instagramjava.R;

import java.util.List;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.viewHolder> {

    private Context mContext;
    private List<String> mTags;
    private List<String> mTagsCount;

    public TagAdapter(
            Context mContext,
            List<String> mTags,
            List<String> mTagsCount
    ) {
        this.mContext = mContext;
        this.mTags = mTags;
        this.mTagsCount = mTagsCount;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item , parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.tag.setText("#" + mTags.get(position));
        holder.noOfPosts.setText(mTagsCount.get(position) + "Posts");
    }

    @Override
    public int getItemCount() {
        return mTags.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        public TextView tag;
        public TextView noOfPosts;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tag = itemView.findViewById(R.id.hash_tag);
            noOfPosts = itemView.findViewById(R.id.no_of_post);
        }
    }

    public void filter(List<String> filterTags , List<String> filterTagsCount){
        this.mTags = filterTags;
        this.mTagsCount = filterTagsCount;

        notifyDataSetChanged();
    }
}
