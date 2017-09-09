package com.example.alek.mvvmexample.di;

import android.app.Application;

import com.example.alek.mvvmexample.mvvm.model.repository.TutorialRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alek on 09/09/2017.
 */

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }


    @Provides
    @Singleton
    TutorialRepository provideTutorialRepository(){
        return new TutorialRepository();
    }
}