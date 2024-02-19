package com.example.firstcomposeproject.domain

import com.example.firstcomposeproject.R


data class FeedPost(
    val id: Int = 0,
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.post_avatar,
    val contentText: String = "lolololololololololololololololololoolooololo \n" +
            " lolololololololololololololololololoolooololo",
    val contentImageResId: Int = R.drawable.post_image,
    val statistics: MutableMap<StatisticType, Int> = hashMapOf(
        StatisticType.VIEWS to 111,
        StatisticType.LIKES to 444,
        StatisticType.SHARES to 222,
        StatisticType.COMMENTS to 333
    )
)