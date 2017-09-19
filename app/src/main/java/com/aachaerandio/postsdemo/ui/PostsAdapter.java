package com.aachaerandio.postsdemo.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aachaerandio.postsdemo.R;
import com.aachaerandio.postsdemo.model.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<Post> posts;
    private Context context;

    public PostsAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.post = posts.get(position);
        holder.title.setText(holder.post.getTitle());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.post_title)
        TextView title;

        public Post post;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
