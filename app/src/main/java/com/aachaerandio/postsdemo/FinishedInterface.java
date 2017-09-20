package com.aachaerandio.postsdemo;

public interface FinishedInterface<T> {
    void onFinished(T items);
    void onError();

}
