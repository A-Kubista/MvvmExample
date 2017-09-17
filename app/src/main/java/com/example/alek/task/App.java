package com.example.alek.task;

import android.app.Application;

import com.example.alek.task.di.AppModule;
import com.example.alek.task.di.DaggerAppComponent;
import com.example.alek.task.di.AppComponent;

/**
 * Created by alek on 14/09/2017.
 */

public class App extends Application {

    private AppComponent mWebComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mWebComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mWebComponent;
    }
}