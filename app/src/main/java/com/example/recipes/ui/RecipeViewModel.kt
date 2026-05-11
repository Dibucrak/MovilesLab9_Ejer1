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

    init { getRecipes() }

    fun getRecipes() {
        viewModelScope.launch {
            _state.value = RecipeState.Loading
            try {
                val recipes = repository.getAllRecipes()
                _state.value = RecipeState.Success(recipes)
            } catch (e: Exception) {
                _state.value = RecipeState.Error("Error: ${e.message}")
            }
        }
    }
}