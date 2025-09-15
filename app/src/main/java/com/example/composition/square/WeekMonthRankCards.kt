package com.example.composition.square

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WeekMonthRankCards(
    weekRank: Int,
    monthRank: Int,
) {
    Column {
        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row {
            RankCard(
                rank = weekRank,
                isWeekRank = true
            )

            RankCard(
                rank = monthRank,
                isWeekRank = false
            )
        }
    }
}

@Preview
@Composable
fun PreviewWeekMonthRankCards() {
    WeekMonthRankCards(
        weekRank = 10,
        monthRank = 10
    )
}