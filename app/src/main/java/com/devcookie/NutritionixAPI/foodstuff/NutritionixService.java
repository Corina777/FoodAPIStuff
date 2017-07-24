package com.devcookie.NutritionixAPI.foodstuff;

import com.devcookie.NutritionixAPI.foodstuff.models.FoodResponse;
import com.devcookie.NutritionixAPI.foodstuff.models.CommonResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface NutritionixService {

    String BASE_URL = "https://trackapi.nutritionix.com";


    @Headers({"Content-Type: application/json", "x-app-id: 15ff6394", "x-app-key: 404a92ca7865942a8ac366d183005a43"})
    @GET("/v2/search/instant")
    Observable<CommonResponse> getCommonFoods(@Query("query") String userInput);

    @Headers({"Content-Type: application/json", "x-app-id: 15ff6394", "x-app-key: 404a92ca7865942a8ac366d183005a43"})
    @POST("/v2/natural/nutrients")
    Observable<FoodResponse> getFood(@Body BodyFood body);

    RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(rxAdapter)
            .build();

    NutritionixService service = retrofit.create(NutritionixService.class);

}
