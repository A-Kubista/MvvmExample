package com.example.alek.task.mvvm.model;

/**
 * Created by alek on 14/09/2017.
 */

public class UserModel {
    private String id;
    private String name;

    public UserModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
