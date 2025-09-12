package com.example.composition.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.composition.example.ExampleContent
import com.example.composition.exercise.ExerciseContent
import com.example.composition.square.SquareContent

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val tabs = listOf("首页", "练习", "范文", "广场")
    val pagerState = rememberPagerState(initialPage = 0) { tabs.size }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier.height(24.dp)
        )

        HomeTopBar(
            selectedIndex = pagerState.currentPage,
            tabs = tabs,
            onTabClick = { index ->
                viewModel.processIntent(HomePageIntent.SelectTab(index))
            },
            pagerState = pagerState,
            onExit = {
                viewModel.processIntent(HomePageIntent.ExitApp)
            },
            navigateToCollection = {
                viewModel.processIntent(HomePageIntent.ClickCollection)
            },
            navigateToHistory = {
                viewModel.processIntent(HomePageIntent.ClickHistory)
            },
            changePeriod = { newPeriodCode ->
                viewModel.processIntent(HomePageIntent.ChangePeriod(newPeriodCode))
            }
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