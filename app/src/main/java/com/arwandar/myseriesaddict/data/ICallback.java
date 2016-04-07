package com.arwandar.myseriesaddict.data;

/**
 * Created by olivi on 31/03/2016.
 */
public interface ICallback<T> {

    void success(T t);

    void failure(Throwable error);
}
