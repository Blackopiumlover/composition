package com.example.composition.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composition.R
import com.example.composition.example.ExampleContent
import com.example.composition.exercise.ExerciseContent
import com.example.composition.square.SquareContent

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val homeState by viewModel.state.collectAsStateWithLifecycle()

    val tabList = homeState.tabList

    val pagerState = rememberPagerState(initialPage = 0) { tabList.size }

    val currentSelectedTabIndex = homeState.currentSelectedTabIndex

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.mipmap.bg_common_page))
    ) {
        Spacer(
            modifier = Modifier.height(24.dp)
        )

        HomeTopBar(
            selectedIndex = pagerState.currentPage,
            tabs = tabList,
            onTabClick = { index ->
                viewModel.processIntent(HomePageIntent.SelectTab(index))
            },
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

        // 同步Tab选择状态
        LaunchedEffect(currentSelectedTabIndex) {
            pagerState.animateScrollToPage(currentSelectedTabIndex)
        }

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