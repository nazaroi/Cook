package com.sample.cook.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey @SerializedName("recipeId") val id: String,
    val name: String,
    val description: String,
    @SerializedName("imageUrl") val imageUri: String?,
    val type: String
    )