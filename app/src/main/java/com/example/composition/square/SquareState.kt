package com.example.composition.square

import com.example.composition.data.EssayItem
import com.example.composition.data.essayItem

data class SquareState(
    val weekRank: Int = 0,
    val monthRank: Int = 0,
    val tabList: List<String> = listOf("最新", "最热", "我的精选"),
    val currentSelectedIndex: Int = 0,
    val essayList: List<EssayItem> = List(30) { essayItem }
)