package com.example.alek.task.di;

import android.app.Application;

import com.example.alek.task.mvvm.model.repository.DetailedUserRepository;
import com.example.alek.task.mvvm.model.repository.UserRepository;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alek on 14/09/2017.
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
    UserRepository provideUserRepository(){
        return new UserRepository();
    }



    @Provides
    @Singleton
    DetailedUserRepository provideDetailedUserRepository(){
        return new DetailedUserRepository();
    }

}