package com.aachaerandio.postsdemo;

import android.support.annotation.NonNull;

import com.aachaerandio.postsdemo.model.User;
import com.aachaerandio.postsdemo.service.PostApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserName {

    private PostApiService postApiService;

    public void execute(int userId, final FinishedInterface<User> listener){
        postApiService = new PostApiService();

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
