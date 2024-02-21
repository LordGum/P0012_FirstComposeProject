package com.example.firstcomposeproject.domain

data class FeedPost(
    val id: String,
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:00",
    val communityImageUrl: String,
    val contentText: String,
    val contentImageUrl: String?,
    val statistics: MutableMap<StatisticType, Int> = hashMapOf(
        StatisticType.VIEWS to 111,
        StatisticType.LIKES to 444,
        StatisticType.SHARES to 222,
        StatisticType.COMMENTS to 333
    )
)
