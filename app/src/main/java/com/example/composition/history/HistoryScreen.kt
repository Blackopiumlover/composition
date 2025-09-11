package com.example.composition.history

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HistoryScreen(
    onBack: () -> Unit
) {
    Column {
        Text(
            text = "This is History Screen."
        )

        Button(
            onClick = onBack
        ) {
            Text(
                text = "Back To Home Screen"
            )
        }
    }
}