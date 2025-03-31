package com.example.colorchooser

import androidx.compose.ui.graphics.Color

data class ColorChooserUiState(
    val currentColorName: String = "",
    val currentColor: Color = Color.Black,
    val expanded: Boolean = false
)
