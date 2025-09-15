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
    val topicName: String
)