package com.aachaerandio.postsdemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.aachaerandio.postsdemo.Constants;
import com.aachaerandio.postsdemo.R;
import com.aachaerandio.postsdemo.model.Post;

public class HomeActivity extends AppCompatActivity implements PostsFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setToolBar();

        PostsFragment postsFragment = new PostsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, postsFragment).commit();
    }

    private void setToolBar() {
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(R.string.posts_app);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public void onPostClicked(Post post) {
        startDetailPostActivity(post);
    }

    private void startDetailPostActivity(Post post)
    {
        Intent intent = new Intent(this, PostDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.POST, post);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
