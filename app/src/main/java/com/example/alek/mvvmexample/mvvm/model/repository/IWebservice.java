package com.example.alek.mvvmexample.mvvm.model.repository;

import com.example.alek.mvvmexample.mvvm.model.entites.web.Tutorial;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by alek on 08/09/2017.
 */

public interface IWebservice {
        /**
         * @GET declares an HTTP GET request
         * @Path("user") annotation on the userId parameter marks it as a
         * replacement for the {user} placeholder in the @GET path
         * @param tutorialId
         */
        @GET("/tutorials/{tutorial}")
        Call<Tutorial> getTutorial(@Path("tutorial") int tutorialId);
}
