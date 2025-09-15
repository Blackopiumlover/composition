package com.example.composition.square

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SquareContent(
    viewModel: SquareViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val weekRank = state.weekRank
    val monthRank = state.monthRank

    val tabList = state.tabList
    val currentSelectedIndex = state.currentSelectedIndex

    val essayList = state.essayList

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 16.dp)

    ) {
        item(
            span = { GridItemSpan(3) }
        ) {
            WeekMonthRankCards(
                weekRank = weekRank,
                monthRank = monthRank,
            )
        }

        item(
            span = { GridItemSpan(3) }
        ) {
            TabRowAndTopicDrawer(
                tabList = tabList,
                currentSelectedIndex = currentSelectedIndex,
            )
        }

        items(
            items = essayList,
        ) { essay ->
            SquareEssayCard(
                essay = essay
            )
        }
    }
}

@Preview
@Composable
fun PreviewSquareContent() {
    SquareContent()
}