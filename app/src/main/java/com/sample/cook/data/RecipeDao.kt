package com.sample.cook.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes ORDER BY name")
    fun getRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE type=:type ORDER BY name")
    fun getRecipesByType(type: String): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE id = :id")
    fun getRecipe(id: String): LiveData<Recipe>

    @Insert
    fun insert(recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)

    @Insert
    fun insertAll(recipes: List<Recipe>)
}
