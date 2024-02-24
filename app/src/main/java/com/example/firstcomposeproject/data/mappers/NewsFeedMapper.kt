package com.example.firstcomposeproject.data.mappers

import com.example.firstcomposeproject.data.model.CommentsResponseDto
import com.example.firstcomposeproject.data.model.NewsFeedResponseDto
import com.example.firstcomposeproject.domain.entities.FeedPost
import com.example.firstcomposeproject.domain.entities.PostComment
import com.example.firstcomposeproject.domain.entities.StatisticType
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.absoluteValue
import javax.inject.Inject

class NewsFeedMapper @Inject constructor()  {

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

    fun mapResponseToComments(response: CommentsResponseDto): List<PostComment> {
        val result = mutableListOf<PostComment>()
        val comments = response.content.comments
        val profiles = response.content.profiles
        for (comment in comments) {
            if (comment.text.isBlank()) continue
            val author = profiles.firstOrNull { it.id == comment.authorId } ?: continue
            val postComment = PostComment(
                id = comment.id,
                authorName = "${author.firstName} ${author.lastName}",
                authorAvatarUrl = author.avatarUrl,
                commentText = comment.text,
                publicationDate = mapTimestampToDate(comment.date)
            )
            result.add(postComment)
        }
        return result
    }
    

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp)
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
    }
}