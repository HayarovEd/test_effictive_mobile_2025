package com.edurda77.data.remote

import retrofit2.http.GET

interface TestApi {
    @GET("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun getCourses(): List<CourseDto>
}