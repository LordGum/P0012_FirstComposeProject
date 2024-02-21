package com.example.firstcomposeproject.data.mappers

import com.example.firstcomposeproject.data.model.NewsFeedResponseDto
import com.example.firstcomposeproject.domain.FeedPost
import com.example.firstcomposeproject.domain.StatisticType
import kotlin.math.absoluteValue

class NewsFeedMapper {

    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups

        for (post in posts) {
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: break
            val feedPost = FeedPost(
                id = post.id,
                communityName = group.name,
                publicationDate = post.date.toString(),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = hashMapOf(
                    StatisticType.LIKES to post.likes.count,
                    StatisticType.VIEWS to  post.views.count,
                    StatisticType.SHARES to post.reposts.count,
                    StatisticType.COMMENTS to post.comments.count
                )
            )
            result.add(feedPost)
        }
        return result
    }
}