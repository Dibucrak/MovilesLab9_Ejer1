package com.example.recipes.data.repository

import com.example.recipes.data.local.RecipeDao
import com.example.recipes.data.model.Recipe
import com.example.recipes.data.remote.RecipeApi

class RecipeRepository(
    private val api: RecipeApi,
    private val dao: RecipeDao
) {
    suspend fun getCombinedRecipes(): List<Recipe> {
        return try {
            val apiData = api.getRecipes().recipes
            val localData = dao.getAllLocalRecipes()
            localData + apiData
        } catch (e: Exception) {
            dao.getAllLocalRecipes()
        }
    }

    suspend fun saveRecipe(recipe: Recipe) {
        dao.insertRecipe(recipe)
    }
}