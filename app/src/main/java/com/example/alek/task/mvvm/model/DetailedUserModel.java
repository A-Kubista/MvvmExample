package com.example.alek.task.mvvm.model;

/**
 * Created by alek on 14/09/2017.
 */

public class DetailedUserModel {
    private String id;
    private String name;
    private String age;
    private String email;
    private Boolean isFemale;
    private String[] hobbies;
    private String img_url;
    private String back_url;


    /**
     *  Default constructor
     */
    public DetailedUserModel(String id, String name) {
        this.email = "";
        this.id = id;
        this.name = name;
        this.age = "";
        this.isFemale = false;
        this.hobbies = new String[0];
        this.img_url = "";
        this.back_url = "";
    }

    public DetailedUserModel(String id, String name,String email, String age, Boolean isFemale, String[] hobbies, String img_url, String back_url) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.isFemale = isFemale;
        this.hobbies = hobbies;
        this.img_url = img_url;
        this.back_url = back_url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public Boolean getFemale() {
        return isFemale;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getBack_url() {
        return back_url;
    }

    public String getEmail() {
        return email;
    }
}

