package com.example.recipes.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipes.ui.RecipeViewModel
import com.example.recipes.ui.RecipeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(recipeId: Int, viewModel: RecipeViewModel, onBack: () -> Unit) {
    val state = viewModel.state.value
    val recipe = (state as? RecipeState.Success)?.recipes?.find { it.id == recipeId }

    recipe?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(it.name) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                        }
                    }
                )
            }
        ) { padding ->
            LazyColumn(modifier = Modifier.padding(padding).fillMaxSize()) {
                item {
                    AsyncImage(
                        model = it.image,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().height(250.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Ingredientes", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                items(it.ingredients) { ingredient ->
                    Text("• $ingredient", modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp))
                }
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                        Text("Instrucciones", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        it.instructions.forEachIndexed { index, step ->
                            Text("${index + 1}. $step", modifier = Modifier.padding(vertical = 4.dp))
                        }
                    }
                }
            }
        }
    }
}