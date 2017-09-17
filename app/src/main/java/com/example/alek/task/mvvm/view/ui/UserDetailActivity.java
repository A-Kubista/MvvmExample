package com.example.alek.task.mvvm.view.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alek.task.App;
import com.example.alek.task.R;
import com.example.alek.task.mvvm.model.Callback;
import com.example.alek.task.mvvm.model.DetailedUserModel;
import com.example.alek.task.mvvm.view.custom.LifecycleAppCompatActivity;
import com.example.alek.task.mvvm.viewmodel.CustomViewModelFactory;
import com.example.alek.task.mvvm.viewmodel.DatailedUserViewModel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a single User detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link UserListActivity}.
 */
public class UserDetailActivity extends LifecycleAppCompatActivity {

    private static final int EMAIL_REQUEST_CODE = 1;

    private DatailedUserViewModel mViewModel;

    @Inject
    public CustomViewModelFactory mCustomViewModelFactory;
    
    @BindView(R.id.imageViewProfile)
    public ImageView image_view_profile;
    @BindView(R.id.imageViewBack)
    public ImageView image_view_back;
    @BindView(R.id.toolbar_layout)
    public CollapsingToolbarLayout appBarLayout;
    @BindView(R.id.fab)
    public FloatingActionButton fab;
    @BindView(R.id.reaveal_bg)
    public View reveal_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((App)getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
        setToolBar();
        inTransition();
        fab.setOnClickListener(view ->
                attemptSendingEmail(mViewModel.getUser().getValue().getEmail(),view));

        String user_id;
        String user_name;
        Boolean two_pane;

        user_id = getIntent().getStringExtra(UserDetailFragment.ARG_USER_ID);
        user_name = getIntent().getStringExtra(UserDetailFragment.ARG_USER_NAME);
        two_pane = getIntent().getBooleanExtra(UserDetailFragment.ARG_TWO_PANE,false);

        mViewModel = ViewModelProviders.of(this, mCustomViewModelFactory).get(DatailedUserViewModel.class);
        mViewModel.init(user_id,user_name);

        LiveData<DetailedUserModel> user =  mViewModel.getUser();
        if(user != null) {
            user.observe(this,
                    this::updateUi);
        }


        if (savedInstanceState == null) {
            UserDetailFragment fragment;
            Bundle arguments = new Bundle();
            arguments.putString(UserDetailFragment.ARG_USER_ID,user_id);
            arguments.putString(UserDetailFragment.ARG_USER_NAME,user_name);
            arguments.putBoolean(UserDetailFragment.ARG_TWO_PANE,two_pane);
            fragment = new UserDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.user_detail_container, fragment)
                    .commit();
        }

    }



    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void attemptSendingEmail(String email,View view) {
        if(email != null && !email.isEmpty()){
            Intent intent_email = new Intent(Intent.ACTION_SEND);
            intent_email.putExtra(Intent.EXTRA_EMAIL,new String[] { email });
            intent_email.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.email_subject));
            intent_email.putExtra(Intent.EXTRA_TEXT,getString(R.string.email_content));
            intent_email.setType("message/rfc822");

            startActivityForResult(Intent.createChooser(intent_email,
                    "Choose an Email client:"), EMAIL_REQUEST_CODE);
        }else{
            Snackbar.make(view, getString(R.string.no_mail), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.email), null).show();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == EMAIL_REQUEST_CODE) {
            String msg;
            if(resultCode == RESULT_OK) {
                msg = getString(R.string.email_success);
            } else {
                msg = getString(R.string.email_fail);
            }
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            outTransition(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void updateUi(DetailedUserModel userModel){
        appBarLayout.setTitle(userModel.getName());
        loadImage(userModel.getImg_url(),image_view_profile);
        loadImage(userModel.getBack_url(),image_view_back);
    }

    private void loadImage(String img_url, ImageView image_view) {
        if(img_url != null && !img_url.isEmpty()) {
            Picasso.with(this)
                    .load(img_url)
                    .noFade()
                    .error(R.color.colorPrimaryDark)
                    .placeholder(R.color.colorPrimaryDark)
                    .into(image_view);
        }
    }

    @Override
    public void onBackPressed() {
        // no data needed for callback
        outTransition(null);
    }

    @Override
    protected void transtionOutCallback(Callback callback) {
        // always finish
        finish();
    }

    @Override
    protected FloatingActionButton getFab() {
        return fab;
    }

    @Override
    protected View getRevealBg() {
        return reveal_bg;
    }


}
