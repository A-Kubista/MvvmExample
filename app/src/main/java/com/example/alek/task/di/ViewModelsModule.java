package com.example.alek.task.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


import com.example.alek.task.mvvm.viewmodel.CustomViewModelFactory;
import com.example.alek.task.mvvm.viewmodel.DatailedUserViewModel;
import com.example.alek.task.mvvm.viewmodel.UserSelectorViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by alek on 14/09/2017.
 */

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserSelectorViewModel.class)
    abstract ViewModel bindUserViewModel(UserSelectorViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DatailedUserViewModel.class)
    abstract ViewModel bindDetailedUserModel(DatailedUserViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(CustomViewModelFactory factory);
}