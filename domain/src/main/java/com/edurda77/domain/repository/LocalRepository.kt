package com.edurda77.domain.repository

import com.edurda77.domain.model.Course
import com.edurda77.domain.utils.DataError
import com.edurda77.domain.utils.ResultWork
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface LocalRepository {
    suspend fun insertCourse(
       course: Course
    ): ResultWork<Unit, DataError.LocalError>

    suspend fun getAllCourses(): Flow<ResultWork<List<Course>, DataError.LocalError>>
    suspend fun deleteCourse(id: Int): ResultWork<Unit, DataError.LocalError>
}