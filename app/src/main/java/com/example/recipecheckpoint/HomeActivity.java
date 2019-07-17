package com.example.recipecheckpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.recipecheckpoint.Adapter.RecipeAdapter;
import com.example.recipecheckpoint.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btMyRecipes = findViewById(R.id.btMyRecipes);
        btMyRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserRecipesActivity.class);
                startActivity(intent);
            }
        });

        final List<Recipe> recipesRv = new ArrayList<>();

        VolleySingleton.getInstance(HomeActivity.this).getAllRecipes(new Consumer<List<Recipe>>() {
            @Override
            public void accept(List<Recipe> recipes) {
                for (Recipe recipe : recipes) {
                    Recipe recipeList = new Recipe(recipe.getTitle(), recipe.getIngredients(), recipe.getDescription());
                    recipesRv.add(recipeList);
                }

                RecyclerView mRecyclerView;
                RecyclerView.Adapter mAdapter;
                RecyclerView.LayoutManager layoutManager;
                mRecyclerView = findViewById(R.id.rvRecipeList);
                layoutManager = new LinearLayoutManager(HomeActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mAdapter = new RecipeAdapter(recipesRv);
                mRecyclerView.setAdapter(mAdapter);

                mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Recipe recipe = recipesRv.get(position);
                        RecipeSingleton.getInstance().setRecipe(recipe);
                        Intent goToDescription = new Intent(HomeActivity.this, DescriptionRecipeActivity.class);
                        startActivity(goToDescription);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

            }
        });
    }
}
