package com.example.recipes.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recipes.ui.RecipeState
import com.example.recipes.ui.RecipeViewModel
import com.example.recipes.ui.components.RecipeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(viewModel: RecipeViewModel, onRecipeClick: (Int) -> Unit) {
    val state = viewModel.state.value
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Gourmet Mix", fontWeight = FontWeight.Bold) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }, containerColor = Color(0xFFFF6D00)) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (state) {
                is RecipeState.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
                is RecipeState.Success -> {
                    LazyColumn {
                        items(state.recipes) { recipe ->
                            RecipeCard(recipe = recipe, onRecipeClick = onRecipeClick)
                        }
                    }
                }
                is RecipeState.Error -> Text("Error", Modifier.align(Alignment.Center))
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Nueva Receta") },
                    text = {
                        Column {
                            TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
                            TextField(value = ingredients, onValueChange = { ingredients = it }, label = { Text("Ingredientes (separados por coma)") })
                        }
                    },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.addRecipe(name, ingredients)
                            showDialog = false
                        }) { Text("Guardar") }
                    }
                )
            }
        }
    }
}