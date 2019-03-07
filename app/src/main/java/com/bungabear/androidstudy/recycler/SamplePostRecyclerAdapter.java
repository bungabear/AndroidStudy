package com.bungabear.androidstudy.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bungabear.androidstudy.R;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by Minjae Son on 2018-07-29.
 */
public class SamplePostRecyclerAdapter extends RecyclerView.Adapter<SamplePostRecyclerAdapter.PostViewHolder> {

    private ArrayList<PostItem> items = new ArrayList<>();
    private final PostItemClickListener postItemClickListener;

    public SamplePostRecyclerAdapter(PostItemClickListener listener){
        this.postItemClickListener = listener;
    }

    @NonNull
    @Override
    public SamplePostRecyclerAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_post, parent, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SamplePostRecyclerAdapter.PostViewHolder holder, int position) {
        holder.set(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(PostItem item){
        items.add(item);
    }

    public static class PostItem {
        public int id;
        public int userId;
        public String title;
        public String body;
        public PostItem(int id, int userId, String title, String body){
            this.id = id;
            this.userId = userId;
            this.title = title;
            this.body = body;
        }

        public PostItem(JsonObject json){
            this.id = json.get("id").getAsInt();
            this.userId = json.get("userId").getAsInt();
            this.title = json.get("title").getAsString();
            this.body = json.get("body").getAsString();
        }
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        private View v;
        private TextView tvId, tvUserId, tvTitle;
        private ImageView ivIcon;
        public PostViewHolder(View itemView) {
            super(itemView);
            v = itemView;
        }

        public void set(final PostItem item){
            tvId = v.findViewById(R.id.tv_post_id);
            tvUserId = v.findViewById(R.id.tv_post_userid);
            tvTitle = v.findViewById(R.id.tv_post_title);
            ivIcon = v.findViewById(R.id.iv_post_icon);
            ivIcon.setTransitionName("POST_TRANSITION_" + getAdapterPosition());
            tvId.setText("id : " +item.id);
            tvUserId.setText("userId : " +item.userId);
            String title = item.title;
            tvTitle.setText("title : " + title);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postItemClickListener.onPostClickListener(item, ivIcon, getAdapterPosition());
                }
            });
        }
    }

    public static interface PostItemClickListener{
        void onPostClickListener(SamplePostRecyclerAdapter.PostItem item, ImageView sharedImageView, int position);
    }
}
