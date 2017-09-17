package com.example.alek.task.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.alek.task.mvvm.model.DetailedUserModel;
import com.example.alek.task.mvvm.model.repository.DetailedUserRepository;

import javax.inject.Inject;

/**
 * Created by alek on 15/09/2017.
 */

public class DatailedUserViewModel extends ViewModel {

    @Inject
    public DetailedUserRepository userRepository;
    @Inject
    public Application mApplication;



    LiveData<DetailedUserModel> user;
    String id;
    String name;



    public DatailedUserViewModel(){
    }

    @Inject
    public DatailedUserViewModel(DetailedUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void init(String id, String user_name) {
        if (this.user != null && this.user.getValue().getId().equals(id)) {
            return;
        }

        this.id = id;
        this.name = user_name;
        user = userRepository.getUser(id,mApplication.getApplicationContext());

        if( user == null) {
           MutableLiveData<DetailedUserModel> undefined_user = new MutableLiveData<DetailedUserModel>();
            undefined_user.setValue(new DetailedUserModel(id,name));
            user = undefined_user;
        }
    }

    public LiveData<DetailedUserModel> getUser() {
        return  user;
    }


}
