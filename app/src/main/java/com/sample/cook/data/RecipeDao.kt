package com.sample.cook.data

import androidx.room.Query

interface RecipeDao {

    @Query("SELECT * FROM recipes ORDER BY name")
    fun getRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE id=  :recipeId")
    fun getRecipe(recipeId: String): Recipe

}