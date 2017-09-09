package com.example.alek.mvvmexample;

import android.app.Application;

import com.example.alek.mvvmexample.di.AppModule;
import com.example.alek.mvvmexample.di.DaggerWebComponent;
import com.example.alek.mvvmexample.di.WebComponent;
import com.example.alek.mvvmexample.di.WebModule;

/**
 * Created by alek on 09/09/2017.
 */

public class App extends Application {

    private WebComponent mWebComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mWebComponent = DaggerWebComponent.builder()
                .appModule(new AppModule(this))
                .webModule(new WebModule("blablalbalbal"))
                .build();
    }

    public WebComponent getWebComponent() {
        return mWebComponent;
    }
}