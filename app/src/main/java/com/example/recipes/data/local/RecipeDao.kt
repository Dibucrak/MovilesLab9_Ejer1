package com.example.recipes.data.local

import androidx.room.*
import com.example.recipes.data.model.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes ORDER BY id DESC")
    suspend fun getAllLocalRecipes(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)
}