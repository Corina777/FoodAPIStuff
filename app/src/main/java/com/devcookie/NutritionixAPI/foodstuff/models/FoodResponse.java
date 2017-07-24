
package com.devcookie.NutritionixAPI.foodstuff.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodResponse {

    @SerializedName("foods")
    @Expose
    private List<Food> foods;

    public FoodResponse(){
        foods = new ArrayList<>();
    }

    public List<Food> getFoods()
    {
        return foods;
    }


}
