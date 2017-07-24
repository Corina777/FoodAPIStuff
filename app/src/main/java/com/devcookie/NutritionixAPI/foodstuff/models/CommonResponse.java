package com.devcookie.NutritionixAPI.foodstuff.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class CommonResponse {

    @SerializedName("common")
    @Expose
    private List<Common> commonFood;

    public CommonResponse(){
        commonFood = new ArrayList<>();
    }

    public List<Common> getCommonFood() {
        return commonFood;
    }



}
