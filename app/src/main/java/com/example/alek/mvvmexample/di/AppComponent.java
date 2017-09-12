package com.example.alek.mvvmexample.di;

import com.example.alek.mvvmexample.mvvm.view.ui.LoginActivity;
import com.example.alek.mvvmexample.mvvm.view.ui.TutorialScrollingActivity;
import com.example.alek.mvvmexample.mvvm.viewmodel.LoginViewModel;
import com.example.alek.mvvmexample.mvvm.viewmodel.TutorialViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alek on 09/09/2017.
 */
@Singleton
@Component(modules={AppModule.class, WebModule.class,ViewModelsModule.class})
public interface AppComponent {
        void inject(TutorialScrollingActivity activity);
        void inject(LoginActivity acitivty);
    }
