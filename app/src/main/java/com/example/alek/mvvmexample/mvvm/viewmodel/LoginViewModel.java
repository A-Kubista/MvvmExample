package com.example.alek.mvvmexample.mvvm.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alek.mvvmexample.App;
import com.example.alek.mvvmexample.R;
import com.example.alek.mvvmexample.di.AppComponent;
import com.example.alek.mvvmexample.di.DaggerAppComponent;
import com.example.alek.mvvmexample.mvvm.model.SingleLiveEvent;
import com.example.alek.mvvmexample.mvvm.model.TutorialModel;
import com.example.alek.mvvmexample.mvvm.model.UserModel;
import com.example.alek.mvvmexample.mvvm.model.repository.TutorialRepository;
import com.example.alek.mvvmexample.mvvm.model.repository.UserRepository;
import com.example.alek.mvvmexample.mvvm.view.ui.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

/**
 * Created by alek on 08/09/2017.
 */

public class LoginViewModel extends ViewModel{
    private LiveData<UserModel> userModel;
    private MutableLiveData<Boolean> login_status ;




    public UserRepository userRepository;
    @Inject
    public Application myApp;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    public LoginViewModel(){

    }

    @Inject
    public LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<UserModel> getUserModel() {
        return userModel;
    }

    public MutableLiveData<Boolean> getLogin_status() {
        return login_status;
    }

    public void init() {
        if (this.userModel != null) {
            return;
        }
        userModel = new MutableLiveData<>();
        login_status = new MutableLiveData<>();
    }


    public void firebaseTest(){
        // RequiresPermission.Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    public boolean validateLogin(AutoCompleteTextView mEmailView, EditText mPasswordView) {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();



        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.

        if(userRepository.validatePassword(password)){
            mPasswordView.setError(myApp.getApplicationContext().getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (userRepository.validateEmail(email)) {
            mEmailView.setError( myApp.getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        }

        return !cancel;
    }



}
