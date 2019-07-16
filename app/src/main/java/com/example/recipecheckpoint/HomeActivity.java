package com.example.recipecheckpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.recipecheckpoint.Adapter.RecipeAdapter;
import com.example.recipecheckpoint.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final List<Recipe> recipes = new ArrayList<>();
        Recipe carbonara = new Recipe("Carbonara", "Lardons", "creme");
        Recipe blanquette = new Recipe("blanquette", "farine , lardons, veau, carottes, laurier, vin blanc", "beurre + farine d'abord");
        Recipe carbonara2 = new Recipe("Carbonara", "Lardons", "creme");
        Recipe carbonara3 = new Recipe("Carbonara", "Lardons", "creme");
        Recipe carbonara4 = new Recipe("Carbonara", "Lardons", "creme");
        Recipe carbonara5 = new Recipe("Carbonara", "Lardons", "creme");
        recipes.add(carbonara);
        recipes.add(blanquette);
        recipes.add(carbonara2);
        recipes.add(carbonara3);
        recipes.add(carbonara4);
        recipes.add(carbonara5);

        RecyclerView mRecyclerView;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager layoutManager;
        mRecyclerView = findViewById(R.id.rvRecipeList);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecipeAdapter(recipes);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Recipe recipe = recipes.get(position);
                RecipeSingleton.getInstance().setRecipe(recipe);
                Intent goToDescription = new Intent(HomeActivity.this, DescriptionRecipeActivity.class);
                startActivity(goToDescription);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
