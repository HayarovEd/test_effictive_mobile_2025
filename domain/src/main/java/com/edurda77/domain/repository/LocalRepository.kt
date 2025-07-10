package com.edurda77.domain.repository

import com.edurda77.domain.model.Course
import com.edurda77.domain.utils.DataError
import com.edurda77.domain.utils.ResultWork
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface LocalRepository {
    suspend fun insertCourse(
        id: Int,
        price: String,
        publishDate: LocalDate,
        rate: String,
        startDate: LocalDate,
        text: String,
        title: String
    ): ResultWork<Unit, DataError.LocalError>

    suspend fun getAllCourses(): Flow<ResultWork<List<Course>, DataError.LocalError>>
    suspend fun deleteCourse(id: Int): ResultWork<Unit, DataError.LocalError>
}