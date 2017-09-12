package com.example.alek.mvvmexample;

import android.app.Application;

import com.example.alek.mvvmexample.di.AppModule;
import com.example.alek.mvvmexample.di.DaggerAppComponent;
import com.example.alek.mvvmexample.di.AppComponent;
import com.example.alek.mvvmexample.di.WebModule;

/**
 * Created by alek on 09/09/2017.
 */

public class App extends Application {

    private AppComponent mWebComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mWebComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this,"kkk"))
                .webModule(new WebModule("blablalbalbal"))
                .build();
    }

    public AppComponent getAppComponent() {
        return mWebComponent;
    }
}