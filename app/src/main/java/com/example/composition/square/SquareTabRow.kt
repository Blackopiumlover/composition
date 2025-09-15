package com.example.composition.square

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composition.R

@Composable
fun SquareTabRow(
    titles: List<String>,
    selectedIndex: Int,
    modifier: Modifier = Modifier
) {
    val selectedColor = Color(0xFF7273EB)
    val unselectedBase = Color(0xFF505182)
    val unselectedColor = unselectedBase.copy(alpha = 0.65f)
    Row(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        titles.forEachIndexed { index, title ->
            val selected = index == selectedIndex
            Column(
                modifier = Modifier
                    .padding(
                        start = if (index == 0) 0.dp else 12.dp,
                        end = if (index == titles.lastIndex) 0.dp else 12.dp,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    color = if (selected) selectedColor else unselectedColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                if (selected) {
                    Image(
                        painter = painterResource(id = R.mipmap.ic_tab_bottom_line),
                        contentDescription = null,
                        modifier = Modifier
                            .height(4.dp)
                            .width(24.dp)
                    )
                } else {
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSquareTabRow() {
    SquareTabRow(
        titles = listOf("最近", "最热", "我的精选"),
        selectedIndex = 0
    )
}