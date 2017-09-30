package com.masotech.bakingapp.retrofit;

import com.masotech.bakingapp.model.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by maysaraodeh on 19/09/2017.
 */

public interface IRecipe {
    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipe();
}
