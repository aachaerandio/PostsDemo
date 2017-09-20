package com.aachaerandio.postsdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aachaerandio.postsdemo.Constants;
import com.aachaerandio.postsdemo.R;
import com.aachaerandio.postsdemo.model.Post;
import com.aachaerandio.postsdemo.model.User;
import com.aachaerandio.postsdemo.presenter.PostDetailsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PostDetailsFragment extends Fragment implements PostDetailsPresenter.UserInterface {

    Unbinder unbinder;
    private PostDetailsPresenter postDetailsPresenter;
    private Post post;

    @BindView(R.id.post_title)
    TextView title;
    @BindView(R.id.post_body)
    TextView postBody;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.number_of_comments)
    TextView numComments;

    public PostDetailsFragment()
    {
    }

    public static PostDetailsFragment getInstance(Post post) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.POST, post);
        PostDetailsFragment postDetailsFragment = new PostDetailsFragment();
        postDetailsFragment.setArguments(args);

        return postDetailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post_details, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null)
        {
            Post post = (Post) getArguments().get(Constants.POST);
            if (post != null)
            {
                this.post = post;
                postDetailsPresenter = new PostDetailsPresenter();
                postDetailsPresenter.setView(this);
                postDetailsPresenter.loadDetails(post);
            }
        }
    }

    @Override
    public void showDetails(Post post, User user, int comments) {
        title.setText(post.getTitle());
        postBody.setText(post.getBody());
        userName.setText(user.getUsername());
        String result = getResources().getQuantityString(R.plurals.comments, comments, comments);
        numComments.setText(String.valueOf(result));
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }
}
