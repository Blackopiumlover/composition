package com.example.composition.example

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.composition.data.Essay

@Composable
fun EssayGrid(
    essayList: List<Essay>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .size(704.dp, 664.dp)
            .clip(RoundedCornerShape(24.dp))
            .padding(horizontal = 8.dp)
    ) {
        item(
            span = { GridItemSpan(2) }
        ) {
            Spacer(
                modifier = Modifier.height(8.dp)
            )
        }

        items(essayList) { essay ->
            EssayCard(
                essay = essay
            )
        }
    }
}