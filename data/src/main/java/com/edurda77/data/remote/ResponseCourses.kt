package com.edurda77.data.remote


import com.google.gson.annotations.SerializedName

data class ResponseCourses(
    @SerializedName("courses")
    val courses: List<CourseDto>
)