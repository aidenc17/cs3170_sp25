package com.example.charactercreator.data

data class CharacterCreatorUiState(
    val name: String = "Warrior",
    val stats: Map<String, Int> = mapOf(
        "stamina" to 19,
        "strength" to 18,
        "agility" to 8,
        "intellect" to 5
    ),
    val maxStats: Int = 25
)
