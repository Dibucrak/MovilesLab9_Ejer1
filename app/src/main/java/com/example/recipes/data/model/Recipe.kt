package com.example.recipes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.recipes.data.local.Converters as LocalConverters

data class RecipeResponse(
    val recipes: List<Recipe>
)

@Entity(tableName = "recipes")
@TypeConverters(LocalConverters::class)
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val prepTimeMinutes: Int,
    val difficulty: String,
    val cuisine: String,
    val image: String,
    val rating: Double = 5.0,
    val isManual: Boolean = false
)