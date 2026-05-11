package com.example.recipes.data.remote

class RecipeApi {
}package com.example.recipes.data.remote

import com.example.recipes.RecipeResponse
import retrofit2.http.GET

interface RecipeApi {
    @GET("recipes")
    suspend fun getRecipes(): RecipeResponse

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
}