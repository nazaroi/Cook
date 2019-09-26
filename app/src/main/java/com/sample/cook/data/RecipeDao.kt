package com.sample.cook.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes ORDER BY name")
    fun getRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    fun getRecipe(recipeId: String): LiveData<Recipe>

    @Insert
    fun insert(recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)

    @Insert
    fun insertAll(recipes: List<Recipe>)
}
