package com.example.composition.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onExit: () -> Unit,
    navigateToCollection: () -> Unit,
    navigateToHistory: () -> Unit,
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
        Spacer(
            modifier = Modifier.height(24.dp)
        )

        HomeTopBar(
            selectedIndex = 0,
            tabs = listOf("首页", "练习", "范文", "广场"),
            onExit = onExit,
            navigateToCollection = navigateToCollection,
            navigateToHistory = navigateToHistory,
            changePeriod = changePeriod
        )
    }
}