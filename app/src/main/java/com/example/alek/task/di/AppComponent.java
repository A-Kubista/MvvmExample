package com.example.alek.task.di;

import com.example.alek.task.mvvm.view.ui.UserDetailActivity;
import com.example.alek.task.mvvm.view.ui.UserDetailFragment;
import com.example.alek.task.mvvm.view.ui.UserListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alek on 14/09/2017.
 */
@Singleton
@Component(modules={AppModule.class,ViewModelsModule.class})
public interface AppComponent {
        void inject(UserListActivity activity);
        void inject(UserDetailFragment fragment);
        void inject(UserDetailActivity activity);
    }
