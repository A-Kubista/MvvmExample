package com.example.alek.task.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.alek.task.mvvm.model.UserModel;
import com.example.alek.task.mvvm.model.repository.UserRepository;

import java.util.LinkedList;

import javax.inject.Inject;

/**
 * Created by alek on 14/09/2017.
 */

public class UserSelectorViewModel extends ViewModel{
    private LiveData<UserModel> user;
    private LiveData<LinkedList<UserModel>> user_list;
    @Inject
    public UserRepository userRepository;
    @Inject
    Application mApplication;




    public UserSelectorViewModel(){
    }

    @Inject
    public UserSelectorViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void init() {
        if (this.user != null) {
            return;
        }
        //user = tutorialRepo.getTutorial(tutorialId);
        user = new MutableLiveData<>();
        user_list = userRepository.getUsers_list(mApplication.getApplicationContext());
    }

    public LiveData<UserModel> getUser() {
        return user;
    }

    public LiveData<LinkedList<UserModel>> getUser_list() {
        return user_list;
    }

}
