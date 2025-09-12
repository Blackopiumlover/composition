package com.example.composition.example

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ExampleContent(

) {
    Text(
        text = "This is Example.",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxSize()
    )
}