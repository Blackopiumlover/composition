package com.example.composition.home

data class HomeState(
    val currentSelectedTabIndex: Int = 0
) {
    val tabList = listOf("首页", "练习", "范文", "广场")
}