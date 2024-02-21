package com.example.firstcomposeproject.data.model

import com.google.gson.annotations.SerializedName

data class ViewDto(
    @SerializedName("count") val count: Int
)