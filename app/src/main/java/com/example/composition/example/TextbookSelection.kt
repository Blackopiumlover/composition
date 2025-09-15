package com.example.composition.example

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composition.R
import com.example.composition.data.Textbook
import com.example.composition.data.textbookList

@Composable
fun TextbookSelection(
    selectedIndex: Int,
    textbooks: List<Textbook>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.wrapContentSize()
            .padding(
                top = 20.dp
            )
    ) {
        textbooks.forEachIndexed { index, textbook ->
            val isSelected = selectedIndex == index

            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .size(80.dp, 40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isSelected) Color.Transparent else Color(0x40D4D2E3))
            ) {
                if (isSelected) {
                    Image(
                        painter = painterResource(id = R.mipmap.bc_example_selected_textbook_background),
                        contentDescription = "Selected Textbook Background",
                        modifier = Modifier
                            .size(80.dp, 40.dp)
                    )
                }

                Text(
                    text = textbook.categoryName,
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    color = if (isSelected) Color(0xff7273eb) else Color(0x99333333),
                    modifier = Modifier
                        .wrapContentSize()
                )
            }

            if (index < textbooks.lastIndex) {
                Spacer(
                    modifier = Modifier.width(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTextbookSelection() {
    TextbookSelection(
        selectedIndex = 0,
        textbooks = textbookList
    )
}