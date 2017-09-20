package com.aachaerandio.postsdemo;

import android.support.annotation.NonNull;

import com.aachaerandio.postsdemo.model.Post;
import com.aachaerandio.postsdemo.service.PostApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPostList {

    private PostApiService postApiService;

    public void execute(final FinishedInterface<List<Post>> listener){
        postApiService = new PostApiService();

        postApiService.getService().getAllPosts()
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                        if (response.body() != null) {
                            listener.onFinished(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Post>> call, Throwable t) {
                        listener.onError();
                    }
                });
    }
}
