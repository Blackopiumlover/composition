package com.example.composition.square

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composition.R

@Composable
fun TabRowAndTopicDrawer(
    tabList: List<String>,
    currentSelectedIndex: Int
) {
    Column {
        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row {
            Spacer(
                modifier = Modifier.width(32.dp)
            )

            SquareTabRow(
                titles = tabList,
                selectedIndex = currentSelectedIndex
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "全部话题",
                fontSize = 18.sp,
                color = Color(0xA6505182),
                modifier = Modifier
                    .wrapContentSize()
            )

            Spacer(
                modifier = Modifier.width(4.dp)
            )

            Box(
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .paint(painter = painterResource(R.mipmap.bc_square_topic_drawer_open))
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(4.dp)
        )
    }
}

@Preview
@Composable
fun PreviewTabRowAndTopicDrawer() {
    TabRowAndTopicDrawer(
        tabList = listOf("最近", "最热", "我的精选"),
        currentSelectedIndex = 0
    )
}