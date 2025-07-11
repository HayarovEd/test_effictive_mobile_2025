package com.edurda77.main_screen

import com.edurda77.domain.model.Course
import com.edurda77.resources.uikit.UiText

data class MainScreenState(
    val isLoading: Boolean = false,
    val query: String = "",
    val remoteCourses: List<Course> = emptyList(),
    val favoriteCourses: List<Course> = emptyList(),
    val message: UiText? = null
)