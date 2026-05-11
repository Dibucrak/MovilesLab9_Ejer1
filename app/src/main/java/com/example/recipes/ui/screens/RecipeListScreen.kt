package com.example.recipes.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recipes.ui.RecipeState
import com.example.recipes.ui.RecipeViewModel
import com.example.recipes.ui.components.RecipeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(viewModel: RecipeViewModel, onRecipeClick: (Int) -> Unit) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recetas Gourmet", fontWeight = FontWeight.ExtraBold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (state) {
                is RecipeState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is RecipeState.Success -> {
                    LazyColumn {
                        items(state.recipes) { recipe ->
                            RecipeCard(recipe = recipe, onRecipeClick = onRecipeClick)
                        }
                    }
                }
                is RecipeState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}