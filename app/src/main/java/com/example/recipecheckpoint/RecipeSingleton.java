package com.example.recipecheckpoint;

import com.example.recipecheckpoint.model.Recipe;

public class RecipeSingleton {

    private static RecipeSingleton instance;
    private Recipe recipe;

    private RecipeSingleton() {
    }

    public static RecipeSingleton getInstance() {
        if (instance == null) {
            instance = new RecipeSingleton();
        }
        return instance;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
