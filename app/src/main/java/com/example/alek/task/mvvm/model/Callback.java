package com.example.alek.task.mvvm.model;

/**
 * Created by alek on 16/09/2017.
 */

public class Callback<T> {
    public T getMyArg() {
        return myArg;
    }

    T myArg;

    public Callback(T myArg) {
        this.myArg = myArg;
    }
}
