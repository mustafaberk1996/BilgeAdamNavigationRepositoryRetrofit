package com.example.navigationwithtoolbartitlelesson.data.api.model

data class Post(
    val body: String,
    val id: Int = 0,
    val title: String,
    val userId: Int
)