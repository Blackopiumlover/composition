package com.example.composition.data

data class EssayItem(
    // 作者头像
    val authorAvatar: String,
    // 作者姓名
    val authorName: String,
    // 类型，专升本返回空
    val category: String,
    // 类型名称，专升本返回空
    val categoryName: String,
    // 收藏数
    val collectCount: Int,
    // 当前用户是否收藏
    val collected: Boolean,
    // 作文简介
    val compositionBrief: String,
    // 作文标题，可能为空，为空时只展示简介，不展示标题
    val compositionTitle: String,
    // 是否属于当前用户
    val currentUser: Boolean,
    // 上墙时间
    val effectiveTime: String,
    // 作文评级，A/B/C
    val level: String,
    // 作文评级描述
    val levelText: String,
    // 点赞数
    val likeCount: Int,
    // 当前用户是否点过赞
    val liked: Boolean,
    // 作文 ID
    val recordId: Int,
    // 分数
    val score: Int,
    // 作者 ID
    val studentCode: String,
    // 话题列表
    val topicList: List<String>
)