package com.example.alek.task.mvvm.model.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.alek.task.mvvm.model.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.example.alek.task.utils.Utils.FILES_JSN_BASE_PATH;
import static com.example.alek.task.utils.Utils.loadJSONFromAsset;


/**
 * Created by alek on 14/09/2017.
 */

@Singleton
public class UserRepository {

    private static final String FILE_USERS_JSN = "users.json";
    private MutableLiveData<LinkedList<UserModel>> users_list;

    @Inject
    public UserRepository() {
    }


        public LiveData<UserModel> getTutorial(int tutorialId) {
            // This is not an optimal implementation, we'll fix it below
            final MutableLiveData<UserModel> data = new MutableLiveData<>();
            data.setValue(new UserModel("0","Mock Example"));
            return data;
        }


    public LinkedList<UserModel> loadUsersFromAssets(Context context){
        LinkedList<UserModel> users_list;
        String raw_jsn;

        raw_jsn = loadJSONFromAsset(context,FILES_JSN_BASE_PATH + "/" +  FILE_USERS_JSN);
        users_list = parseUsersJSN(raw_jsn);

        return users_list;
    }


    public LinkedList<UserModel>  parseUsersJSN(String raw){
        LinkedList<UserModel> users = new LinkedList<>();

        try {
            JSONObject obj = new JSONObject(raw);
            JSONArray jsonArray = obj.getJSONArray("users");
            for(int i = 0; i < jsonArray.length(); i++){
                try {
                    JSONObject user_jsn = jsonArray.getJSONObject(i);
                    Iterator iterator = user_jsn.keys();
                    String user_id = (String) iterator.next();
                    String user_name = user_jsn.getString(user_id);
                    UserModel user_tmp = new UserModel(user_id,user_name);
                    users.add(user_tmp);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }


    public MutableLiveData<LinkedList<UserModel>> getUsers_list(Context context) {
        if(users_list == null) {
            users_list = new MutableLiveData<LinkedList<UserModel>>();
            users_list.postValue(loadUsersFromAssets(context));
        }
        return users_list;
    }
}
