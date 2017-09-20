package com.aachaerandio.postsdemo.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aachaerandio.postsdemo.presenter.PostsPresenter;
import com.aachaerandio.postsdemo.R;
import com.aachaerandio.postsdemo.model.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<Post> posts;
    private final PostsPresenter.UserInterface view;
    private Context context;

    public PostsAdapter(List<Post> posts, PostsPresenter.UserInterface view) {
        this.posts = posts;
        this.view = view;
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
        holder.itemView.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.post_title)
        TextView title;

        public Post post;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            PostsAdapter.this.view.onPostClicked(post);
        }
    }
}
