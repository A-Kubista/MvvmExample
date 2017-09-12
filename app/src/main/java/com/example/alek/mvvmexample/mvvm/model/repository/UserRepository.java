package com.example.alek.mvvmexample.mvvm.model.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.example.alek.mvvmexample.mvvm.model.TutorialModel;
import com.example.alek.mvvmexample.mvvm.model.UserModel;
import com.example.alek.mvvmexample.mvvm.model.entites.web.Tutorial;
import com.example.alek.mvvmexample.utils.Mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alek on 08/09/2017.
 */

@Singleton
public class UserRepository {


    @Inject
    public UserRepository() {
    }

    private static final int MINIMUM_PASSWORD_LENGTH = 4;

    // ...
        public LiveData<UserModel> getTutorial(int tutorialId) {
            // This is not an optimal implementation, we'll fix it below
            final MutableLiveData<UserModel> data = new MutableLiveData<>();
            data.setValue(new UserModel(0,"Mock Example"));
            return data;
        }

    /**
     *  Simplest validation example
     * @param password
     * @return
     */
    public boolean validatePassword(String password) {
        return (TextUtils.isEmpty(password)  && password.length() >= MINIMUM_PASSWORD_LENGTH);
    }

    /**
     *  Simplest validation example
     * @param email
     * @return
     */
    public boolean validateEmail(String email) {
        return (TextUtils.isEmpty(email) && email.contains("@"));
    }
}
