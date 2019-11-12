package com.example.starbucks_piece;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FoodLab {
    private static FoodLab sFoodLab;
    private List<Food> mFoods;

    private FoodLab(Context context){
        mFoods = new ArrayList<>();

        //pre-load the five food items
        mFoods.add(new Food("Hot Coffees",R.drawable.cappaccino));
        mFoods.add(new Food("Frappuccinos", R.drawable.frappuccino));
        mFoods.add(new Food("Cold Coffees", R.drawable.coldbrew));
        mFoods.add(new Food("Iced Tea", R.drawable.icetea));
        mFoods.add(new Food("Cold Drinks", R.drawable.pinkdrink));
    }

    public static FoodLab get(Context context){
        if(sFoodLab == null){
            sFoodLab = new FoodLab(context);
        }
        return sFoodLab;
    }

    public List<Food> getFoods(){
        return mFoods;
    }

    public Food getFood(UUID id){
        for(Food food: mFoods){
            if(food.getId().equals(id)){
                return food;
            }
        }
        return null;
    }

}
