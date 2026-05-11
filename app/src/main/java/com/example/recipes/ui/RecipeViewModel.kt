package com.example.recipes.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.model.Recipe
import com.example.recipes.data.repository.RecipeRepository
import kotlinx.coroutines.launch

sealed class RecipeState {
    object Loading : RecipeState()
    data class Success(val recipes: List<Recipe>) : RecipeState()
    data class Error(val message: String) : RecipeState()
}

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val _state = mutableStateOf<RecipeState>(RecipeState.Loading)
    val state: State<RecipeState> = _state

    init { loadAll() }

    fun loadAll() {
        viewModelScope.launch {
            _state.value = RecipeState.Loading
            val data = repository.getCombinedRecipes()
            _state.value = RecipeState.Success(data)
        }
    }

    fun addRecipe(name: String, ingredients: String) {
        viewModelScope.launch {
            val newRecipe = Recipe(
                name = name,
                ingredients = ingredients.split(","),
                instructions = listOf("Instrucciones manuales"),
                prepTimeMinutes = 30,
                difficulty = "Media",
                cuisine = "Local",
                image = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c",
                isManual = true
            )
            repository.saveRecipe(newRecipe)
            loadAll()
        }
    }
}