package com.edurda77.data.remote


import com.google.gson.annotations.SerializedName

data class CourseDto(
    @SerializedName("hasLike")
    val hasLike: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("price")
    val price: String,
    @SerializedName("publishDate")
    val publishDate: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("title")
    val title: String
)