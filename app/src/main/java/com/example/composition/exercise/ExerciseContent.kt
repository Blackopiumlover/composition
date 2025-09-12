package com.example.composition.exercise

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ExerciseContent() {
    Text(
        text = "This is Exercise.",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxSize()
    )
}