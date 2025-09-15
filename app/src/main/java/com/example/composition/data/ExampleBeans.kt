package com.example.composition.data

data class Textbook(
    val category: String,
    val categoryList: List<Category>,
    val categoryName: String
)

data class Category(
    val categoryId: Int,
    val categoryName: String,
    val topicList: List<Topic>
)

data class Topic(
    val topicId: Int,
    val topicName: String,
    val essayList: List<Essay>
)

data class Essay(
    val category: String,
    val collectCount: Int,
    val compositionBrief: String,
    val difficulty: Int,
    val difficultyText: String,
    val id: Int,
    val isCollected: Int,
    val stage: Int,
    val stageText: String,
    val titleTranslate: String,
    val topicId: Int,
    val viewCount: Int
)