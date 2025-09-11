package com.example.composition.collection

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CollectionScreen(
    onBack: () -> Unit
) {
    Column {
        Text(
            text = "This is Collection Screen."
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