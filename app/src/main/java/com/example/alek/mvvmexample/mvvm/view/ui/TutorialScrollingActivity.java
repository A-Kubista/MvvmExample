package com.example.alek.mvvmexample.mvvm.view.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.alek.mvvmexample.App;
import com.example.alek.mvvmexample.R;
import com.example.alek.mvvmexample.mvvm.view.customviews.LifecycleAppCompatActivity;
import com.example.alek.mvvmexample.mvvm.viewmodel.TutorialViewModel;

import javax.inject.Inject;

public class TutorialScrollingActivity extends LifecycleAppCompatActivity {

    @Inject
    public TutorialViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((App)getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        repo.getTutorial(1);

        viewModel = ViewModelProviders.of(this).get(TutorialViewModel.class);
        viewModel.init(1);
        viewModel.getTutorialItem().observe(this, user -> {
            // update UI
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
