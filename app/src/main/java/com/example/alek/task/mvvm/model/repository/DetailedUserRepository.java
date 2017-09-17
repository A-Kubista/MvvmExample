package com.example.alek.task.mvvm.model.repository;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.alek.task.mvvm.model.DetailedUserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.example.alek.task.utils.Utils.FILES_JSN_BASE_PATH;
import static com.example.alek.task.utils.Utils.loadJSONFromAsset;


/**
 * Created by alek on 14/09/2017.
 */

@Singleton
public class DetailedUserRepository {

    private static final String FILE_DETAILED_USERS_JSN = "user-details.json";
    private MutableLiveData<LinkedList<DetailedUserModel>> users_list;
    private MutableLiveData<DetailedUserModel> user_cached;

    @Inject
    public DetailedUserRepository() {
    }


    public LinkedList<DetailedUserModel> loadUsersFromAssets(Context context){
        LinkedList<DetailedUserModel> users_list;
        String raw_jsn;

        raw_jsn = loadJSONFromAsset(context,FILES_JSN_BASE_PATH + "/" +  FILE_DETAILED_USERS_JSN);
        users_list = parseUsersJSN(raw_jsn);

        return users_list;
    }

    public LinkedList<DetailedUserModel>  parseUsersJSN(String raw){
        LinkedList<DetailedUserModel> users = new LinkedList<>();

        try {
            JSONObject obj = new JSONObject(raw);
            JSONArray jsonArray = obj.getJSONArray("users");
            for(int i = 0; i < jsonArray.length(); i++){
                try {
                    JSONObject user_jsn = jsonArray.getJSONObject(i);
                    JSONArray hobbies_jsn = user_jsn.optJSONArray("hobbies");
                    String[] hobbies;

                    if(hobbies_jsn != null) {
                        hobbies = new String[hobbies_jsn.length()];

                        for (int j = 0; j < hobbies.length; j++) {
                            hobbies[j] = hobbies_jsn.getString(j);
                        }
                    }else{
                        hobbies = new String[0];
                    }

                    DetailedUserModel user_tmp = new DetailedUserModel(
                            user_jsn.getString("id"),
                            user_jsn.optString("name"),
                            user_jsn.optString("email"),
                            user_jsn.optString("age"),
                            user_jsn.optBoolean("isFemale"),
                            hobbies,
                            user_jsn.optString("image"),
                            user_jsn.optString("back")

                    );
                    users.add(user_tmp);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    public MutableLiveData<DetailedUserModel> getUser(String user_id,Context context) {

        if(user_id == null)
            return null;

        if(users_list == null) {
            users_list = new MutableLiveData<LinkedList<DetailedUserModel>>();
            users_list.setValue(loadUsersFromAssets(context));
        }

        if(user_cached != null && user_cached.getValue().getId().equals(user_id))
            return user_cached;

        for (DetailedUserModel user_tmp : users_list.getValue()){
            if(user_tmp.getId().equals(user_id)){
                user_cached = new MutableLiveData<>();
                user_cached.setValue(user_tmp);
                return user_cached;
            }
        }

        return null;
    }


}
