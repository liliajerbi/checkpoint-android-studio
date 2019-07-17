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
import com.example.recipecheckpoint.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_recipes);

        Button btReturn = findViewById(R.id.btReturnProfile);
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserRecipesActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        final List<Recipe> recipesRv = new ArrayList<>();
        VolleySingleton.getInstance(UserRecipesActivity.this).getOneUSer(UserSingleton.getInstance().getUser(), new Consumer<User>() {
            @Override
            public void accept(User user) {
                for (int i = 0; i < user.getRecipes().size(); i++) {
                    Recipe recipeAdded = new Recipe(user.getRecipes().get(i).getTitle(), user.getRecipes().get(i).getIngredients(), user.getRecipes().get(i).getDescription());
                    recipesRv.add(recipeAdded);
                }

                RecyclerView mRecyclerView;
                RecyclerView.Adapter mAdapter;
                RecyclerView.LayoutManager layoutManager;
                mRecyclerView = findViewById(R.id.rvRecipeProfile);
                layoutManager = new LinearLayoutManager(UserRecipesActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mAdapter = new RecipeAdapter(recipesRv);
                mRecyclerView.setAdapter(mAdapter);

                mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Recipe recipe = recipesRv.get(position);
                        RecipeSingleton.getInstance().setRecipe(recipe);
                        Intent goToDescription = new Intent(UserRecipesActivity.this, DescriptionRecipeActivity.class);
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
