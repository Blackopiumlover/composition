package com.example.composition.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composition.data.Topic

@Composable
fun TopicItem(
    isSelected: Boolean,
    topic: Topic,
    modifier: Modifier = Modifier
) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFEDEEFF),
            Color.Transparent
        )
    )

    Row {
        Spacer(
            modifier = Modifier.width(18.dp)
        )

        Text(
            text = topic.topicName,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = if (isSelected) Color(0xFF7273EB) else Color(0x99333333),
            modifier = modifier
                .size(254.dp, 40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(brush = if (isSelected) gradientBrush else SolidColor(Color.Transparent))
                .padding(
                    start = 12.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )
        )
    }
}

@Preview
@Composable
fun PreviewTopicItem() {
    Column {
        TopicItem(
            isSelected = true,
            topic = Topic(
                topicId = 0,
                topicName = "自我管理"
            )
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        TopicItem(
            isSelected = false,
            topic = Topic(
                topicId = 0,
                topicName = "自我管理"
            )
        )
    }
}