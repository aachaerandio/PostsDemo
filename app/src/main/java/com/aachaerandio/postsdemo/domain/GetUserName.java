package com.aachaerandio.postsdemo.domain;

import android.support.annotation.NonNull;

import com.aachaerandio.postsdemo.FinishedInterface;
import com.aachaerandio.postsdemo.model.User;
import com.aachaerandio.postsdemo.service.PostApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserName {

    private PostApiService postApiService;

    public GetUserName(){
        this.postApiService = new PostApiService();
    }

    public void setPostApiService(PostApiService postApiService) {
        this.postApiService = postApiService;
    }

    public void execute(int userId, final FinishedInterface<User> listener){
        postApiService.getService().getUserById(userId)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if (response.body() != null) {
                            listener.onFinished(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, Throwable t) {
                        listener.onError();
                    }
                });
    }
}
