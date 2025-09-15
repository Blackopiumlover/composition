package com.example.composition.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composition.R

@Composable
fun HomeTabRow(
    selectedIndex: Int,
    tabList: List<String>,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.wrapContentSize()
    ) {
        tabList.forEachIndexed { index, string ->
            val isSelected = selectedIndex == index
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .clickable(
                        onClick = { onTabClick(index) }
                    )
            ) {
                Text(
                    text = string,
                    color = if (isSelected) Color(0xff505182) else Color(0xa6505182),
                    fontSize = if (isSelected) 24.sp else 20.sp,
                    lineHeight = if (isSelected) 32.sp else 28.sp,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Center)
                )

                if (isSelected) {
                    Image(
                        painter = painterResource(id = R.mipmap.ic_home_title_underline),
                        contentDescription = "Selected Tab Underline",
                        modifier = Modifier
                            .size(32.dp, 12.dp)
                            .align(Alignment.BottomCenter)
                    )
                }
            }

            if (index < tabList.lastIndex) {
                Spacer(
                    modifier = Modifier.width(40.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeTabRow() {
    HomeTabRow(
        selectedIndex = 1,
        tabList = listOf("批改", "练习", "范文", "广场"),
        onTabClick = {}
    )
}