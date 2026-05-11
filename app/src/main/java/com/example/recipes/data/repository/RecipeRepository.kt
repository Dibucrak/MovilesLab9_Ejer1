package com.example.recipes.data.repository

class RecipeRepository {
}package com.example.recipes.data.repository

import com.example.recipes.data.remote.RecipeApi
import com.example.recipes.Recipe

class RecipeRepository(private val api: RecipeApi) {
    suspend fun getAllRecipes(): List<Recipe> {
        return try {
            val response = api.getRecipes()
            response.recipes
        } catch (e: Exception) {
            emptyList()
        }
    }
}