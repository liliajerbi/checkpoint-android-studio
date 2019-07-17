package com.example.recipecheckpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.recipecheckpoint.model.Recipe;
import com.example.recipecheckpoint.model.User;

public class AddRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        Button btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etTitle = findViewById(R.id.etTitleRecipe);
                EditText etIngredient = findViewById(R.id.etIngredients);
                EditText etRecipe = findViewById(R.id.etRecipe);

                String titleRecipe = etTitle.getText().toString();
                String ingredientRecipe = etIngredient.getText().toString();
                String recipeDesc = etRecipe.getText().toString();

                final User user = UserSingleton.getInstance().getUser();
                final Recipe recipeAdd = new Recipe();
                recipeAdd.setTitle(titleRecipe);
                recipeAdd.setIngredients(ingredientRecipe);
                recipeAdd.setDescription(recipeDesc);
                VolleySingleton.getInstance(AddRecipeActivity.this).postRecipe(recipeAdd, user, new Consumer<Recipe>() {
                    @Override
                    public void accept(Recipe recipe) {
                        RecipeSingleton.getInstance().setRecipe(recipeAdd);
                        Intent intent = new Intent(AddRecipeActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
