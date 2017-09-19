package com.aachaerandio.postsdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.aachaerandio.postsdemo.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setToolBar();

        PostsFragment postsFragment = new PostsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, postsFragment).commit();
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
}
