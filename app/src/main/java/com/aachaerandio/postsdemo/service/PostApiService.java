package com.aachaerandio.postsdemo.service;

import com.aachaerandio.postsdemo.BuildConfig;
import com.aachaerandio.postsdemo.model.Comment;
import com.aachaerandio.postsdemo.model.Post;
import com.aachaerandio.postsdemo.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class PostApiService {


    public PostService getService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.POST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PostService.class);
    }

    public interface PostService {
        @GET("/posts")
        Call<List<Post>> getAllPosts();

        @GET("/posts/{id}")
        Call<List<Post>> getPostById(@Path("id") int id);

        @GET("/users/{id}")
        Call<User> getUserById(@Path("id") int id);

        @GET("/comments")
        Call<List<Comment>> getCommentsByPostId(@Query("postId") int id);

    }
}
