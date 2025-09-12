package com.example.composition.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HomeContent(

) {
    Text(
        text = "This is Home.",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxSize()
    )
}