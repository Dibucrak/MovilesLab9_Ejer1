package com.example.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipes.data.remote.RetrofitClient
import com.example.recipes.data.repository.RecipeRepository
import com.example.recipes.ui.RecipeViewModel
import com.example.recipes.ui.navigation.Screens
import com.example.recipes.ui.screens.RecipeDetailScreen
import com.example.recipes.ui.screens.RecipeListScreen
import com.example.recipes.ui.theme.RecipesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = RetrofitClient.recipeApi
        val repository = RecipeRepository(api)
        val viewModel = RecipeViewModel(repository)

        setContent {
            RecipesTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screens.RecipeList.route
                ) {
                    composable(Screens.RecipeList.route) {
                        RecipeListScreen(
                            viewModel = viewModel,
                            onRecipeClick = { id ->
                                navController.navigate(Screens.RecipeDetail.createRoute(id))
                            }
                        )
                    }

                    composable(
                        route = Screens.RecipeDetail.route,
                        arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("recipeId") ?: 0
                        RecipeDetailScreen(
                            recipeId = id,
                            viewModel = viewModel,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}