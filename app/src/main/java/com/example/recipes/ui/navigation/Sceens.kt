package com.example.recipes.ui.navigation

sealed class Screens(val route: String) {
    object RecipeList : Screens("recipe_list")
    object RecipeDetail : Screens("recipe_detail/{recipeId}") {
        fun createRoute(recipeId: Int) = "recipe_detail/$recipeId"
    }
}