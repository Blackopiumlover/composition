package com.example.composition.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.composition.example.ExampleContent
import com.example.composition.exercise.ExerciseContent
import com.example.composition.square.SquareContent

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

    val tabs = listOf("首页", "练习", "范文", "广场")
    val pagerState = rememberPagerState(initialPage = 0) { tabs.size }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier.height(24.dp)
        )

        HomeTopBar(
            selectedIndex = 0,
            tabs = tabs,
            pagerState = pagerState,
            onExit = onExit,
            navigateToCollection = navigateToCollection,
            navigateToHistory = navigateToHistory,
            changePeriod = changePeriod
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> HomeContent()
                1 -> ExerciseContent()
                2 -> ExampleContent()
                3 -> SquareContent()
            }
        }
    }
}