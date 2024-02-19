package com.example.firstcomposeproject.domain

data class Comment (
    val id: Int,
    val authorName: String = "Author name",
    val authorAvatarUrl: String = "",
    val contentText: String = "Text of Comment",
    val time: String = "test time 11:00"
)