package com.aachaerandio.postsdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aachaerandio.postsdemo.Constants;
import com.aachaerandio.postsdemo.R;
import com.aachaerandio.postsdemo.model.Post;

public class PostDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        setToolbar();

        if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey(Constants.POST))
            {
                Post post = extras.getParcelable(Constants.POST);
                if (post != null)
                {
                    PostDetailsFragment postDetailsFragment = PostDetailsFragment.getInstance(post);
                    getSupportFragmentManager().beginTransaction().add(R.id.post_details_container, postDetailsFragment).commit();
                }
            }
        }
    }

    private void setToolbar()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(R.string.posts_details);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }
}
