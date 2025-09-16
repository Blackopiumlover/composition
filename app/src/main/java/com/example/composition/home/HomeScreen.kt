package com.example.composition.home

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
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

    // 缓存背景资源
    val backgroundPainter = painterResource(R.mipmap.bg_common_page)

    // 预先创建页面内容，避免在滑动时创建
    val pages = remember(tabList.size) {
        (0 until tabList.size).map { page ->
            when (page) {
                0 -> { @Composable { HomeContent() } }
                1 -> { @Composable { ExerciseContent() } }
                2 -> { @Composable { ExampleContent() } }
                3 -> { @Composable { SquareContent() } }
                else -> { @Composable {} }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = backgroundPainter,
                contentScale = ContentScale.FillBounds
            )
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
            beyondViewportPageCount = tabList.size - 1,
            modifier = Modifier.weight(1f).graphicsLayer {
                // 启用硬件加速层
                compositingStrategy = CompositingStrategy.Offscreen
            },
            // 设置更合适的滑动行为
            pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                state = pagerState,
                orientation = Orientation.Horizontal
            ),
            // 设置更合适的键盘和触摸处理
            userScrollEnabled = true,
            // 添加key参数
            key = { pageIndex -> pages[pageIndex].hashCode() },
            // 使用缓存的页面内容
            pageContent = { page -> pages[page]() }
        )
    }
}