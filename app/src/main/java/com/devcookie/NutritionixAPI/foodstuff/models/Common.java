package com.devcookie.NutritionixAPI.foodstuff.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Common {

    @SerializedName("food_name")
    @Expose
    private String foodName;

    @SerializedName("photo")
    @Expose
    private CommonPhoto photo;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }


    public CommonPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(CommonPhoto photo) {
        this.photo = photo;
    }

    @Override
    public String toString(){
        return "Common {" + "foodname='" + foodName + "}";
    }

}
