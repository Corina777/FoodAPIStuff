package com.devcookie.NutritionixAPI.foodstuff.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CommonPhoto {

    @SerializedName("thumb")
    @Expose
    private String thumb;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
