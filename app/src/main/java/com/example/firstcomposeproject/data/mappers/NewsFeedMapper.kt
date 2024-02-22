package com.example.firstcomposeproject.data.mappers

import com.example.firstcomposeproject.data.model.NewsFeedResponseDto
import com.example.firstcomposeproject.domain.FeedPost
import com.example.firstcomposeproject.domain.StatisticType
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale
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
                communityId = post.communityId,
                communityName = group.name,
                publicationDate = mapTimestampToDate(post.date * 1000),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = hashMapOf(
                    StatisticType.LIKES to post.likes.count,
                    StatisticType.VIEWS to  post.views.count,
                    StatisticType.SHARES to post.reposts.count,
                    StatisticType.COMMENTS to post.comments.count
                ),
                isLiked = post.likes.userLikes > 0
            )
            result.add(feedPost)
        }
        return result
    }

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp)
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
    }
}