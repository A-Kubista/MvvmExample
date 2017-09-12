package com.example.alek.mvvmexample.mvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import com.example.alek.mvvmexample.di.AppComponent;
import com.example.alek.mvvmexample.di.DaggerAppComponent;
import com.example.alek.mvvmexample.mvvm.model.TutorialModel;
import com.example.alek.mvvmexample.mvvm.model.repository.TutorialRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

/**
 * Created by alek on 08/09/2017.
 */

public class TutorialViewModel extends ViewModel{
    private LiveData<TutorialModel> tutorial_item;

    @Inject
    public TutorialRepository tutorialRepo;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    public TutorialViewModel(){
    }

    @Inject
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
