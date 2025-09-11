package com.example.composition.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onExit: () -> Unit,
    navigateToCollection: () -> Unit,
    navigationToHistory: () -> Unit,
    changePeriod: () -> Unit
) {
    Column {
        Text(
            text = "This is Home Screen."
        )

        Button(
            onClick = onExit
        ) {
            Text(
                text = "Exit"
            )
        }

        Button(
            onClick = navigateToCollection
        ) {
            Text(
                text = "Go To Collection"
            )
        }

        Button(
            onClick = navigationToHistory
        ) {
            Text(
                text = "Go To History"
            )
        }

        Button(
            onClick = changePeriod
        ) {
            Text(
                text = "Change Period"
            )
        }
    }
}