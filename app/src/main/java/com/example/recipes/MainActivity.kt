package com.example.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.recipes.data.local.AppDatabase
import com.example.recipes.data.remote.RetrofitClient
import com.example.recipes.data.repository.RecipeRepository
import com.example.recipes.ui.RecipeViewModel
import com.example.recipes.ui.screens.RecipeListScreen
import com.example.recipes.ui.theme.RecipesTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipes.ui.navigation.Screens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(this)
        val repository = RecipeRepository(RetrofitClient.recipeApi, db.recipeDao())
        val viewModel = RecipeViewModel(repository)

        setContent {
            RecipesTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = Screens.RecipeList.route) {
                    composable(Screens.RecipeList.route) {
                        RecipeListScreen(viewModel, onRecipeClick = { })
                    }
                }
            }
        }
    }
}