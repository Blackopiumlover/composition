package com.example.composition.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onExit: () -> Unit,
    navigateToCollection: () -> Unit,
    navigationToHistory: () -> Unit,
    changePeriod: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is HomePageEvent.ExitApp -> onExit()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {




        Text(
            text = "This is Home Screen."
        )

        Button(
            onClick = {
                viewModel.processIntent(HomePageIntent.ExitApp)
            }
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