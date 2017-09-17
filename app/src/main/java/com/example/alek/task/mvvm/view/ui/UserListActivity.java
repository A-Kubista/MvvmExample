package com.example.alek.task.mvvm.view.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.alek.task.App;
import com.example.alek.task.R;

import com.example.alek.task.mvvm.model.Callback;
import com.example.alek.task.mvvm.view.RecyclerViewAdapter;
import com.example.alek.task.mvvm.view.custom.LifecycleAppCompatActivity;
import com.example.alek.task.mvvm.viewmodel.CustomViewModelFactory;
import com.example.alek.task.mvvm.viewmodel.UserSelectorViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a list of Users. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link UserDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class UserListActivity extends LifecycleAppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;


    private UserSelectorViewModel mViewModel;

    @Inject
    public CustomViewModelFactory mViewModelFactory;
    private RecyclerViewAdapter mAdapter;

    @BindView(R.id.fab)
    public FloatingActionButton fab;
    @BindView(R.id.reaveal_bg)
    public View reveal_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((App)getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ButterKnife.bind(this);
        inTransition();

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(UserSelectorViewModel.class);
        mViewModel.init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                Snackbar.make(view, getString(R.string.fab_add_info), Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show());

        if (findViewById(R.id.user_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.user_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        mViewModel.getUser_list().observe(this,
                userModels -> mAdapter.addItems(userModels));
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mAdapter = new RecyclerViewAdapter(this, mTwoPane);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected FloatingActionButton getFab() {
        return fab;
    }

    @Override
    protected View getRevealBg() {
        return reveal_bg;
    }

    @Override
    public void onBackPressed() {
        outTransition(null);
    }

    @Override
    protected void transtionOutCallback(Callback callback) {
        if(callback != null){
            try {
                Intent intent = (Intent) callback.getMyArg();
                startActivity(intent);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            finish();
        }
    }

    public void onUserSelected(Intent intent) {
        outTransition(new Callback<>(intent));
    }

    @Override
    protected void onResume() {
        super.onResume();
        inTransition();
    }
}
