package com.example.alek.mvvmexample.utils;

import com.example.alek.mvvmexample.mvvm.model.TutorialModel;
import com.example.alek.mvvmexample.mvvm.model.entites.web.Tutorial;

/**
 * Created by alek on 09/09/2017.
 */

public  class Mapper {
    public static TutorialModel mapToTutorialModel(Tutorial webModel){
        return new TutorialModel(webModel.getTutorial_id());
    }
}
