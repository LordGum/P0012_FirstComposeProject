package com.example.firstcomposeproject.data.model

import com.google.gson.annotations.SerializedName

data class PostDto(
    @SerializedName("id") val id: Long,
    @SerializedName("source_id") val communityId: Long,

    @SerializedName("date") val date: Long,
    @SerializedName("text") val text: String,
    @SerializedName("attachments") val attachments: List<AttachmentDto>?,

    @SerializedName("comments") val comments: CommentsDto,
    @SerializedName("likes") val likes: LikeDto,
    @SerializedName("reposts") val reposts: RepostDto,
    @SerializedName("views") val views: ViewDto,

    @SerializedName("is_favourite") val isFavourite: Boolean
)
