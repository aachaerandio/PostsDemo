package com.aachaerandio.postsdemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aachaerandio.postsdemo.presenter.PostsPresenter;
import com.aachaerandio.postsdemo.R;
import com.aachaerandio.postsdemo.model.Post;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PostsFragment extends Fragment implements PostsPresenter.UserInterface {

    private PostsPresenter postsPresenter;

    @BindView(R.id.posts_recyclerView)
    RecyclerView recyclerView;

    Unbinder unbinder;
    private PostsAdapter adapter;
    private List<Post> posts = new ArrayList<>();
    private Callback callback;

    public PostsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_posts, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter = new PostsAdapter(posts, this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postsPresenter = new PostsPresenter();
        postsPresenter.setView(this);
        if(savedInstanceState == null) {
            postsPresenter.loadPosts();
        }
    }

    @Override
    public void showPosts(List<Post> posts) {
        this.posts.clear();
        this.posts.addAll(posts);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPostClicked(Post post) {
        callback.onPostClicked(post);
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onDetach() {
        callback = null;
        super.onDetach();
    }

    public interface Callback
    {
        void onPostClicked(Post post);
    }
}
