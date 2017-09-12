package com.example.alek.mvvmexample.mvvm.model.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.alek.mvvmexample.mvvm.model.TutorialModel;
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
public class TutorialRepository {
        @Inject
        public IWebservice webservice;

    @Inject
    public TutorialRepository() {

    }


    // ...
        public LiveData<TutorialModel> getTutorial(int tutorialId) {
            // This is not an optimal implementation, we'll fix it below
            final MutableLiveData<TutorialModel> data = new MutableLiveData<>();
            webservice.getTutorial(tutorialId).enqueue(new Callback<Tutorial>() {
                @Override
                public void onResponse(Call<Tutorial> call, Response<Tutorial> response) {
                    // error case is left out for brevity
                    data.setValue(Mapper.mapToTutorialModel(response.body()));
                }

                @Override
                public void onFailure(Call<Tutorial> call, Throwable t) {

                }
            });
            return data;
        }
}
