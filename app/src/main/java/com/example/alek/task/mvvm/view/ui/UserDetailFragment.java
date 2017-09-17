package com.example.alek.task.mvvm.view.ui;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.alek.task.App;
import com.example.alek.task.R;
import com.example.alek.task.mvvm.model.DetailedUserModel;
import com.example.alek.task.mvvm.viewmodel.CustomViewModelFactory;
import com.example.alek.task.mvvm.viewmodel.DatailedUserViewModel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A fragment representing a single User detail screen.
 * This fragment is either contained in a {@link UserListActivity}
 * in two-pane mode (on tablets) or a {@link UserDetailActivity}
 * on handsets.
 */
public class UserDetailFragment extends LifecycleFragment {

    public static final String ARG_USER_ID = "user_id";
    public static final String ARG_USER_NAME = "user_name";
    public static final String ARG_TWO_PANE = "two_pane";
    private static final int EMAIL_REQUEST_CODE = 1;


    @Inject
    public CustomViewModelFactory mViewModelFactory;

    @BindView(R.id.hobbies_container)
    public LinearLayout hobbiesContainer;
    @BindView(R.id.textViewHobbies)
    public TextView textViewHobbies;
    @BindView(R.id.textViewAge)
    public TextView textViewAge;
    @BindView(R.id.textViewEmail)
    public TextView textViewEmail;
    @BindView(R.id.textViewSex)
    public TextView textViewSex;

    @BindView(R.id.imageViewHobbies)
    public ImageView imageViewHobbiesIcon;
    @BindView(R.id.imageViewEmail)
    public ImageView imageViewMailIcon;
    @BindView(R.id.imageViewAge)
    public ImageView imageViewAgeIcon;
    @BindView(R.id.imageViewSex)
    public ImageView imageViewSexIcon;

    // LARGE UI
    @BindView(R.id.custom_toolbar_large)
    public View custom_toolbar_large;
    @BindView(R.id.imageViewBack)
    public ImageView imageViewBack;
    @BindView(R.id.imageViewProfile)
    public ImageView imageViewProfile;
    @BindView(R.id.fab_custom_large)
    public FloatingActionButton custom_fab_large;
    @BindView(R.id.textViewName)
    public TextView textViewName;

    private DatailedUserViewModel mViewModel;
    private Activity parent_activity;
    private boolean two_pane;

    public UserDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((App)getActivity().getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

        String user_id = getArguments().getString(UserDetailFragment.ARG_USER_ID);
        String user_name = getArguments().getString(UserDetailFragment.ARG_USER_NAME);
        two_pane = getArguments().getBoolean(UserDetailFragment.ARG_TWO_PANE);

        mViewModel = ViewModelProviders.of(getActivity(), mViewModelFactory).get(DatailedUserViewModel.class);
        mViewModel.init(user_id,user_name);
    }

    private void updateUi(DetailedUserModel userModel){
        loadEmail(userModel.getEmail());
        loadSex(userModel.getFemale());
        loadAge(userModel.getAge());
        loadHobbies( userModel.getHobbies());

        updateLargeUI(userModel);
    }

    private void updateLargeUI(DetailedUserModel userModel) {
        if(two_pane){
            custom_toolbar_large.setVisibility(View.VISIBLE);
            custom_fab_large.setVisibility(View.VISIBLE);
            loadProfileImage(userModel.getImg_url(),userModel.getFemale());
            loadImage(userModel.getBack_url(),imageViewBack,R.color.colorPrimaryDark);
            textViewName.setText(userModel.getName());
            custom_fab_large.getDrawable().setAlpha(255);
        }else {
            custom_toolbar_large.setVisibility(View.GONE);
            custom_fab_large.setVisibility(View.GONE);
        }
    }

    private void loadProfileImage(String img_url, Boolean female) {
        int error_img_id;
        if(female)
            error_img_id = R.drawable.female_icon;
        else
            error_img_id = R.drawable.male_icon;
        loadImage(img_url,imageViewProfile,error_img_id);
    }


    private void loadImage(String img_url, ImageView image_view,int error_resource_id) {
        if(img_url != null && !img_url.isEmpty()) {
            Picasso.with(getActivity())
                    .load(img_url)
                    .noFade()
                    .error(error_resource_id)
                    .placeholder(error_resource_id)
                    .into(image_view);
        }
    }

    private void loadEmail(String email) {
        if(email != null && !email.isEmpty()) {
            String text = getString(R.string.email) + " " + email;
            textViewEmail.setText(text);
        }else{
            textViewEmail.setVisibility(View.GONE);
            imageViewMailIcon.setVisibility(View.GONE);
        }
    }

    private void loadSex(boolean isFemale) {
        String sex;
        int sex_icon_id;

        if(isFemale){
            sex = getString(R.string.sex_female);
            sex_icon_id = R.drawable.female_icon;
        }else{
            sex = getString(R.string.sex_male);
            sex_icon_id = R.drawable.male_icon;
        }

        Picasso.with(getActivity())
                .load(sex_icon_id)
                .placeholder(R.color.colorPrimaryDark)
                .into(imageViewSexIcon);

        String text_age = getString(R.string.sex) + " " + sex;
        textViewSex.setText(text_age);
    }

    private void loadAge(String age) {
        if(age != null && !age.isEmpty()) {
            String text_age = getString(R.string.age) + " " + age;
            textViewAge.setText(text_age);
        }else{
            textViewAge.setVisibility(View.GONE);
            imageViewAgeIcon.setVisibility(View.GONE);
        }
    }

    private void loadHobbies(String[] hobbies) {
        if(hobbies.length <= 0){
            textViewHobbies.setVisibility(View.GONE);
            imageViewHobbiesIcon.setVisibility(View.GONE);
        }else {
            for (int i = 0; i < hobbies.length; i++) {
                hobbiesContainer.addView(createHobbyItem(hobbies[i]));
            }
        }
    }

    private View createHobbyItem(String hobby) {
            LayoutInflater vi = (LayoutInflater) parent_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = vi.inflate(R.layout.item_hobby_layout, null);
            ((TextView) v.findViewById(R.id.textViewHobby)).setText(hobby);
            return v;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_detail, container, false);
        ButterKnife.bind(this, rootView);
        parent_activity =  this.getActivity();

        LiveData<DetailedUserModel> user =  mViewModel.getUser();
        if(user != null) {
            user.observe(this,
                    userModel -> updateUi(userModel));
        }
        
        custom_fab_large.setOnClickListener(view -> {
            attemptSendingEmail(mViewModel.getUser().getValue().getEmail(),view);
        });


        return rootView;
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == EMAIL_REQUEST_CODE) {
            String msg;
            if(resultCode == RESULT_OK) {
                msg = getString(R.string.email_success);
            } else {
                msg = getString(R.string.email_fail);
            }
            Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
        }
    }
}
