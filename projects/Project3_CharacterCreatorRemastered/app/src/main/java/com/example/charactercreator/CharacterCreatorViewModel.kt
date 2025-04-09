package com.example.charactercreator

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.charactercreator.data.CharacterCreatorUiState

class CharacterCreatorViewModel : ViewModel() {
    // UI state
    private val _uiState = MutableStateFlow(CharacterCreatorUiState())
    val uiState: StateFlow<CharacterCreatorUiState> = _uiState.asStateFlow()

    // Keep backward compatibility with your existing code
    val stats = mutableStateOf(_uiState.value.stats)
    val baseStats = mutableStateOf(_uiState.value.stats)

    // Method to update a specific stat
    fun updateStat(statName: String, value: Int) {
        val currentStats = _uiState.value.stats.toMutableMap()
        currentStats[statName] = value

        // Update both states
        _uiState.value = _uiState.value.copy(stats = currentStats)
        stats.value = currentStats

        // Debug log
        println("ViewModel updated $statName to $value, new stats: ${stats.value}")
    }

    // Method to set the base stats when a new character is selected
    fun setBaseStats(newBaseStats: Map<String, Int>) {
        _uiState.value = _uiState.value.copy(stats = newBaseStats)

        // Update the mutable states for backward compatibility
        baseStats.value = newBaseStats
        stats.value = newBaseStats

        // Debug log
        println("Base stats set to: $newBaseStats")
    }

    init {
        resetCharacter()
    }

    private fun resetCharacter() {
        _uiState.value = CharacterCreatorUiState()
        setBaseStats(mapOf(
            "stamina" to 19,
            "strength" to 18,
            "agility" to 8,
            "intellect" to 5
        ))
    }
}
