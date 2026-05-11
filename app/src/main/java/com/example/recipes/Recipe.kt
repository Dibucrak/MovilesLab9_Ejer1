package com.example.recipes
data class RecipeResponse(
    val recipes: List<Recipe>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val prepTimeMinutes: Int,
    val difficulty: String,
    val cuisine: String,
    val caloriesPerServing: Int,
    val image: String,
    val rating: Double,
    val reviewCount: Int,
    val tags: List<String>
)
