package com.example.recipecheckpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DescriptionRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_recipe);

        TextView tvTitle = findViewById(R.id.tvRecipeDesc);
        tvTitle.setText(RecipeSingleton.getInstance().getRecipe().getTitle());


        TextView tvIngredient = findViewById(R.id.tvGetIngredients);
        tvIngredient.setText(RecipeSingleton.getInstance().getRecipe().getIngredients());

        TextView tvRecipe = findViewById(R.id.tvDescRecipe);
        tvRecipe.setText(RecipeSingleton.getInstance().getRecipe().getDescription());

        Button btReturn = findViewById(R.id.btReturn);
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(DescriptionRecipeActivity.this, HomeActivity.class);
                startActivity(goToHome);
            }
        });
    }
}
