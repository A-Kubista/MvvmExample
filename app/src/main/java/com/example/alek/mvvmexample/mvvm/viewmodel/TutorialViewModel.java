package com.example.alek.mvvmexample.mvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.alek.mvvmexample.mvvm.model.TutorialModel;
import com.example.alek.mvvmexample.mvvm.model.repository.TutorialRepository;

import javax.inject.Inject;

/**
 * Created by alek on 08/09/2017.
 */

public class TutorialViewModel extends ViewModel {
    private LiveData<TutorialModel> tutorial_item;

    @Inject
    public TutorialRepository tutorialRepo;

    @Inject
    public TutorialViewModel(){
       // DaggerWebComponent.builder().build().inject(this);
    }

    public TutorialViewModel(TutorialRepository tutorialRepo) {
        this.tutorialRepo = tutorialRepo;
    }

    public void init(int tutorialId) {
        if (this.tutorial_item != null) {
            return;
        }
        //tutorial_item = tutorialRepo.getTutorial(tutorialId);
        tutorial_item = new MutableLiveData<>();
    }


    public LiveData<TutorialModel> getTutorialItem() {
        return tutorial_item;
    }
}
