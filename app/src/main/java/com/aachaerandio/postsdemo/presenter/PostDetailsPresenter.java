package com.aachaerandio.postsdemo.presenter;

import com.aachaerandio.postsdemo.FinishedInterface;
import com.aachaerandio.postsdemo.domain.GetComments;
import com.aachaerandio.postsdemo.domain.GetUserName;
import com.aachaerandio.postsdemo.model.Comment;
import com.aachaerandio.postsdemo.model.Post;
import com.aachaerandio.postsdemo.model.User;

import java.util.List;

public class PostDetailsPresenter {

    private PostDetailsPresenter.UserInterface view;
    private GetUserName getUserName;
    private GetComments getComments;

    public void setView(PostDetailsPresenter.UserInterface view) {
        this.view = view;
    }

    public void loadDetails(final Post post) {
        getUserName = new GetUserName();
        getComments = new GetComments();
        getUserName.execute(post.getUserId(), new FinishedInterface<User>() {
            @Override
            public void onFinished(final User user) {
                getComments.execute(post.getId(), new FinishedInterface<List<Comment>>() {
                    @Override
                    public void onFinished(List<Comment> comments) {
                        view.showDetails(post, user, comments.size());
                    }

                    @Override
                    public void onError() {
                    }
                });
            }

            @Override
            public void onError() {

            }
        });

    }

    public interface UserInterface {
        void showDetails(Post post, User user, int size);
    }
}
