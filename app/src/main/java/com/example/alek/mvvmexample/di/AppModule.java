package com.example.alek.mvvmexample.di;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.alek.mvvmexample.mvvm.model.repository.TutorialRepository;
import com.example.alek.mvvmexample.mvvm.model.repository.UserRepository;
import com.example.alek.mvvmexample.mvvm.viewmodel.LoginViewModel;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

/**
 * Created by alek on 09/09/2017.
 */

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application,String string) {
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


    @Provides
    @Singleton
    UserRepository provideUserRepository(){
        return new UserRepository();
    }




}