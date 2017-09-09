package com.example.alek.mvvmexample.di;

import com.example.alek.mvvmexample.mvvm.view.ui.TutorialScrollingActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alek on 09/09/2017.
 */
@Singleton
@Component(modules={AppModule.class, WebModule.class})
public interface WebComponent {
        void inject(TutorialScrollingActivity activity);
        //void inject(TutorialViewModel exampleViewModel);
        // void inject(MyFragment fragment);
        // void inject(MyService service);
    }
