package com.aachaerandio.postsdemo.presenter;

import com.aachaerandio.postsdemo.FinishedInterface;
import com.aachaerandio.postsdemo.domain.GetPostList;
import com.aachaerandio.postsdemo.model.Post;
import java.util.List;

public class PostsPresenter {

    private UserInterface view;
    private GetPostList getPostList;

    public void setView(PostsPresenter.UserInterface view)
    {
        this.view = view;
        loadPosts();
    }

    public void loadPosts() {
        getPostList = new GetPostList();
        getPostList.execute(new FinishedInterface<List<Post>>() {
            @Override
            public void onFinished(List<Post> posts) {
                view.showPosts(posts);
            }

            @Override
            public void onError() {

            }
        });
    }

    public interface UserInterface {
        void showPosts(List<Post> posts);
        void onPostClicked(Post post);

    }
}
