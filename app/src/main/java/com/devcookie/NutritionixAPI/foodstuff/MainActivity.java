package com.devcookie.NutritionixAPI.foodstuff;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.devcookie.NutritionixAPI.foodstuff.models.Common;
import com.devcookie.NutritionixAPI.foodstuff.models.CommonResponse;
import com.devcookie.NutritionixAPI.foodstuff.models.Food;
import com.devcookie.NutritionixAPI.foodstuff.models.FoodResponse;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.devcookie.NutritionixAPI.foodstuff.NutritionixService.service;


public class MainActivity extends AppCompatActivity {

    private String inputFood;
    private Subscription searchSubscription;
    private Subscription editTextSubscription;
    private CompositeSubscription compositeSubscription;

    @BindView(R.id.inputText)
    AutoCompleteTextView inputText;
    @BindView(R.id.unit_tv)
    TextView servingUnit;
    @BindView(R.id.calories_tv)
    TextView calories;
    @BindView(R.id.total_fat_tv)
    TextView totalFat;
    @BindView(R.id.saturated_fat_tv)
    TextView saturatedFat;
    @BindView(R.id.sodium_tv)
    TextView sodium;
    @BindView(R.id.total_carbo_tv)
    TextView totalCarbs;
    @BindView(R.id.dietary_fibers_tv)
    TextView dietaryFibers;
    @BindView(R.id.sugars_tv)
    TextView sugars;
    @BindView(R.id.protein_tv)
    TextView protein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (!isInternetConnection()) {
            Toast.makeText(this, R.string.no_Internet, Toast.LENGTH_LONG).show();
        }

        compositeSubscription = new CompositeSubscription();

        editTextSubscription = RxTextView.textChanges(inputText)
                .skip(1)
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        return charSequence.length() > 2;
                    }
                })
                .debounce(100, TimeUnit.MILLISECONDS)
                .switchMap(new Func1<CharSequence, Observable<CommonResponse>>() {
                    @Override
                    public Observable<CommonResponse> call(CharSequence charSequence) {
                        Log.d("switchMap", charSequence.toString());
                        Observable<CommonResponse> callForCommonFood = service.getCommonFoods(charSequence.toString());
                        return callForCommonFood
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommonResponse>() {
                    @Override
                    public void onError(Throwable e) {
                        Log.d("onError", e.getMessage());
                    }

                    @Override
                    public void onNext(CommonResponse commons) {

                        List<String> dropDownList = new ArrayList<>();
                        List<Common> commonFoods = commons.getCommonFood();
                        for (Common commonFood : commonFoods) {
                            dropDownList.add(commonFood.getFoodName());
                        }

                        inputText.setAdapter(makeAutoCompleteAdapter(dropDownList));
                        inputText.showDropDown();
                        hideSoftKeyboard();
                    }

                    @Override
                    public void onCompleted() {
                        Log.d("onCompleted", "common");

                    }
                });

        compositeSubscription.add(editTextSubscription);
    }

    @OnClick(R.id.buttonSearch)
    public void searchFood(View view) {
        inputFood = getUserInput();
        Log.d("inputfood: ", inputFood);
        Observable<FoodResponse> callForFood = service.getFood(new BodyFood(inputFood));

        searchSubscription = callForFood
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FoodResponse>() {
                    @Override
                    public void onNext(FoodResponse foodResponse) {
                        if (foodResponse.getFoods().isEmpty()) {
                            showUnknownFoodMessage(inputFood);
                        } else {
                            Food food = foodResponse.getFoods().get(0);
                            Log.d("Food: ", food.toString());
                            servingUnit.setText(food.getServingUnit());
                            calories.setText(food.getNfCalories().toString());
                            totalFat.setText(food.getNfTotalFat().toString());
                            saturatedFat.setText(food.getNfSaturatedFat().toString());
                            sodium.setText(food.getNfSodium().toString());
                            totalCarbs.setText(food.getNfTotalCarbohydrate().toString());
                            dietaryFibers.setText(food.getNfDietaryFiber().toString());
                            sugars.setText(food.getNfSugars().toString());
                            protein.setText(food.getNfProtein().toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException) e;
                            int code = response.code();
                            Log.d("onError", "response code: " + code);
                            showUnknownFoodMessage(inputFood);
                        }
                    }

                    @Override
                    public void onCompleted() {
                        Log.d("search", "onCompleted");
                    }
                });
        compositeSubscription.add(searchSubscription);
    }

    public String getUserInput() {
        return inputText.getText().toString();
    }

    public void showUnknownFoodMessage(String inputFood) {
        Toast.makeText(this, "What is this " + inputFood + " you are searching?", Toast.LENGTH_LONG).show();
    }

    public ArrayAdapter makeAutoCompleteAdapter(List<String> dropDownList) {
        return new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, dropDownList);
    }

    public boolean isInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
    }
}
