package com.edurda77.favorite_screen

import com.edurda77.domain.model.Course
import com.edurda77.resources.uikit.UiText

data class FavoriteScreenState(
    val favoriteCourses: List<Course> = emptyList(),
    val message: UiText? = null
)