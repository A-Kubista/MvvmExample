package com.example.alek.task.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by alek on 14/09/2017.
 */

public class Utils {
    public static final String FILES_JSN_BASE_PATH = "data_jsn";

    public static  String loadJSONFromAsset(Context context, String path) throws  WrongArgumentsException{
        String json = null;
        if(context != null && path != null && !path.isEmpty()) {
            try {
                InputStream is = context.getAssets().open(path);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }else{
            throw  new WrongArgumentsException("Context and path must not be empty or null ");
        }
        return json;
    }
}
