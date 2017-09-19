package com.aachaerandio.postsdemo.service;

import com.aachaerandio.postsdemo.BuildConfig;
import com.aachaerandio.postsdemo.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

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
        Call<List<Post>> getUserById(@Path("id") int id);

        @GET("/comments/{id}")
        Call<List<Post>> getComentsByPostId(@Path("id") int id);

    }
}
