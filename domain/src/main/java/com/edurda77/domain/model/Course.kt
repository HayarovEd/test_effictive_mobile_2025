package com.edurda77.domain.model

import kotlinx.datetime.LocalDate


data class Course(
    //val hasLike: Boolean,
    val id: Int,
    val price: String,
    val publishDate: LocalDate,
    val rate: String,
    val startDate: LocalDate,
    val text: String,
    val title: String
)