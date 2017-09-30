package com.masotech.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.masotech.bakingapp.Adapters.RecipeIngredientAdapter;
import com.masotech.bakingapp.Adapters.RecipeStepAdapter;
import com.masotech.bakingapp.model.Ingredient;
import com.masotech.bakingapp.model.Recipe;
import com.masotech.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import static com.masotech.bakingapp.MainActivity.SELECTED_RECIPES;

/**
 * Created by maysaraodeh on 21/09/2017.
 */

public class RecipeDetailsFragment extends Fragment {

    ArrayList<Recipe> recipe;
    //    String recipeName;
    private RecipeIngredientAdapter mRecipeIngredientAdapter;
    private RecyclerView mIngredientRecyclerView;
    private RecyclerView mStepRecyclerView;
    private RecipeStepAdapter mRecipeStepAdapter;

    public RecipeDetailsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ActionBar actionBar;
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRecipeStepAdapter = new RecipeStepAdapter((RecipeDetailsActivity)getActivity());

        recipe = new ArrayList<>();


        Intent intent = getActivity().getIntent();
        recipe = intent.getParcelableArrayListExtra(SELECTED_RECIPES);
        List<Ingredient> ingredients = recipe.get(0).getIngredients();
        List<Step> steps = recipe.get(0).getSteps();

        View rootView = inflater.inflate(R.layout.recipe_detail_fragment, container, false);

        ArrayList<String> recipeIngredientsForWidgets= new ArrayList<>();

        ingredients.forEach((a) ->
                {
                recipeIngredientsForWidgets.add(a.getIngredient()+"\n"+
                        "Quantity: "+a.getQuantity().toString()+"\n"+
                        "Measure: "+a.getMeasure()+"\n");
                }
        );

        BakingService.startBakingService(getContext(),recipeIngredientsForWidgets);

        mIngredientRecyclerView = (RecyclerView) rootView.findViewById(R.id.recipe_ingredient);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mIngredientRecyclerView.setLayoutManager(mLayoutManager);


        LinearLayoutManager mLayoutManagerSteps = new LinearLayoutManager(getActivity());
        mStepRecyclerView = (RecyclerView) rootView.findViewById(R.id.recipe_step);
        mStepRecyclerView.setLayoutManager(mLayoutManagerSteps);
        mRecipeStepAdapter.setRecipeData(getActivity() , steps);
        mStepRecyclerView.setAdapter(mRecipeStepAdapter);



        mRecipeIngredientAdapter = new RecipeIngredientAdapter(getActivity(), ingredients);
        mIngredientRecyclerView.setAdapter(mRecipeIngredientAdapter);


        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("attached ya 5ra" , "attached");
    }
}
