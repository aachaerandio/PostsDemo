package com.aachaerandio.postsdemo;

import android.support.annotation.NonNull;

import com.aachaerandio.postsdemo.model.Comment;
import com.aachaerandio.postsdemo.service.PostApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetComments {

    private PostApiService postApiService;

    public void execute(int postId, final FinishedInterface<List<Comment>> listener){
        postApiService = new PostApiService();

        postApiService.getService().getCommentsByPostId(postId)
                .enqueue(new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Comment>> call, @NonNull Response<List<Comment>> response) {
                        if (response.body() != null) {
                            listener.onFinished(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Comment>> call, Throwable t) {
                        listener.onError();
                    }
                });
    }
}
