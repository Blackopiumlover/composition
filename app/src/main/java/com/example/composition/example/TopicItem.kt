package com.example.composition.example

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    onTopicSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    // 缓存渐变背景，避免每次重组时重新创建
    val gradientBrush = remember {
        Brush.horizontalGradient(
            colors = listOf(
                Color(0xFFEDEEFF),
                Color.Transparent
            )
        )
    }

    // 缓存文本颜色，避免每次重组时重新计算
    val textColor = remember(isSelected) {
        if (isSelected) Color(0xFF7273EB) else Color(0x99333333)
    }

    // 缓存背景画笔，避免每次重组时重新计算
    val backgroundBrush = remember(isSelected) {
        if (isSelected) gradientBrush else SolidColor(Color.Transparent)
    }

    // 优化修饰符链，减少嵌套层级
    val textModifier = remember(isSelected) {
        Modifier
            .size(254.dp, 40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(brush = backgroundBrush)
            .padding(
                start = 12.dp,
                top = 8.dp,
                bottom = 8.dp
            )
    }

    // 使用单一修饰符链，减少布局计算
    Row(
        modifier = modifier
            .clickable(onClick = onTopicSelected)
            .padding(start = 18.dp)
    ) {
        Text(
            text = topic.topicName,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = textColor,
            modifier = textModifier
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
                topicName = "自我管理",
                essayList = emptyList()
            ),
            onTopicSelected = {}
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        TopicItem(
            isSelected = false,
            topic = Topic(
                topicId = 0,
                topicName = "自我管理",
                essayList = emptyList()
            ),
            onTopicSelected = {}
        )
    }
}