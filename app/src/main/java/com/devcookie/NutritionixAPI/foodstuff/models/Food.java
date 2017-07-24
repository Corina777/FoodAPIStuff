
package com.devcookie.NutritionixAPI.foodstuff.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Food {

    @SerializedName("food_name")
    @Expose
    private String foodName;
    @SerializedName("serving_qty")
    @Expose
    private Integer servingQty;
    @SerializedName("serving_unit")
    @Expose
    private String servingUnit;
    @SerializedName("serving_weight_grams")
    @Expose
    private Integer servingWeightGrams;
    @SerializedName("nf_calories")
    @Expose
    private Double nfCalories;
    @SerializedName("nf_total_fat")
    @Expose
    private Double nfTotalFat;
    @SerializedName("nf_saturated_fat")
    @Expose
    private Double nfSaturatedFat;
    @SerializedName("nf_cholesterol")
    @Expose
    private Integer nfCholesterol;
    @SerializedName("nf_sodium")
    @Expose
    private Double nfSodium;
    @SerializedName("nf_total_carbohydrate")
    @Expose
    private Double nfTotalCarbohydrate;
    @SerializedName("nf_dietary_fiber")
    @Expose
    private Double nfDietaryFiber;
    @SerializedName("nf_sugars")
    @Expose
    private Double nfSugars;
    @SerializedName("nf_protein")
    @Expose
    private Double nfProtein;
    @SerializedName("nf_potassium")
    @Expose
    private Double nfPotassium;
    @Expose
    private FoodPhoto photo;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getServingQty() {
        return servingQty;
    }

    public void setServingQty(Integer servingQty) {
        this.servingQty = servingQty;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    public Integer getServingWeightGrams() {
        return servingWeightGrams;
    }

    public void setServingWeightGrams(Integer servingWeightGrams) {
        this.servingWeightGrams = servingWeightGrams;
    }

    public Double getNfCalories() {
        return nfCalories;
    }

    public void setNfCalories(Double nfCalories) {
        this.nfCalories = nfCalories;
    }

    public Double getNfTotalFat() {
        return nfTotalFat;
    }

    public void setNfTotalFat(Double nfTotalFat) {
        this.nfTotalFat = nfTotalFat;
    }

    public Double getNfSaturatedFat() {
        return nfSaturatedFat;
    }

    public void setNfSaturatedFat(Double nfSaturatedFat) {
        this.nfSaturatedFat = nfSaturatedFat;
    }

    public Integer getNfCholesterol() {
        return nfCholesterol;
    }

    public void setNfCholesterol(Integer nfCholesterol) {
        this.nfCholesterol = nfCholesterol;
    }

    public Double getNfSodium() {
        return nfSodium;
    }

    public void setNfSodium(Double nfSodium) {
        this.nfSodium = nfSodium;
    }

    public Double getNfTotalCarbohydrate() {
        return nfTotalCarbohydrate;
    }

    public void setNfTotalCarbohydrate(Double nfTotalCarbohydrate) {
        this.nfTotalCarbohydrate = nfTotalCarbohydrate;
    }

    public Double getNfDietaryFiber() {
        return nfDietaryFiber;
    }

    public void setNfDietaryFiber(Double nfDietaryFiber) {
        this.nfDietaryFiber = nfDietaryFiber;
    }

    public Double getNfSugars() {
        return nfSugars;
    }

    public void setNfSugars(Double nfSugars) {
        this.nfSugars = nfSugars;
    }

    public Double getNfProtein() {
        return nfProtein;
    }

    public void setNfProtein(Double nfProtein) {
        this.nfProtein = nfProtein;
    }

    public Double getNfPotassium() {
        return nfPotassium;
    }

    public void setNfPotassium(Double nfPotassium) {
        this.nfPotassium = nfPotassium;
    }

    public FoodPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(FoodPhoto photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodName='" + foodName + '\'' +
                ", servingQty=" + servingQty +
                ", servingUnit='" + servingUnit + '\'' +
                ", servingWeightGrams=" + servingWeightGrams +
                ", nfCalories=" + nfCalories +
                ", nfTotalFat=" + nfTotalFat +
                ", nfSaturatedFat=" + nfSaturatedFat +
                ", nfCholesterol=" + nfCholesterol +
                ", nfSodium=" + nfSodium +
                ", nfTotalCarbohydrate=" + nfTotalCarbohydrate +
                ", nfDietaryFiber=" + nfDietaryFiber +
                ", nfSugars=" + nfSugars +
                ", nfProtein=" + nfProtein +
                ", nfPotassium=" + nfPotassium +
                '}';
    }
}
