package com.aachaerandio.postsdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aachaerandio.postsdemo.PostsPresenter;
import com.aachaerandio.postsdemo.R;
import com.aachaerandio.postsdemo.model.Post;

import java.util.List;

public class PostsFragment extends Fragment implements PostsPresenter.OnFinishedListener{

    private PostsPresenter postsPresenter;

    public PostsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_posts, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postsPresenter = new PostsPresenter();
        postsPresenter.loadPosts(this);
    }

    @Override
    public void onFinished(List<Post> items) {

    }

    @Override
    public void onError() {

    }
}
