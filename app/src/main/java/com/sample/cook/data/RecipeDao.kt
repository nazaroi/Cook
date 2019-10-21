package com.sample.cook.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes ORDER BY name")
    fun getRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE type=:type ORDER BY name")
    fun getRecipesByType(type: String): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE id IN (:ids) ORDER BY name")
    fun getRecipesByIds(ids: Set<String>): List<Recipe>

    @Query("SELECT * FROM recipes WHERE id = :id")
    fun getRecipe(id: String): LiveData<Recipe>

    // SQLite does not have a boolean data type. Room maps it to an INTEGER column, mapping true to
    // 1 and false to 0
    @Query("SELECT * FROM recipes WHERE isFavorite = 1")
    fun getFavoriteRecipes(): LiveData<List<Recipe>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)

    @Insert
    fun insertAll(recipes: List<Recipe>)
}
