package com.edurda77.data.repository

import com.edurda77.data.local.CourseDataBase
import com.edurda77.data.local.CourseEntity
import com.edurda77.domain.model.Course
import com.edurda77.domain.repository.LocalRepository
import com.edurda77.domain.utils.DataError
import com.edurda77.domain.utils.ResultWork
import com.edurda77.domain.utils.convertStringToLocalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate

class LocalRepositoryImpl(
    db: CourseDataBase
) : LocalRepository {

    private val dao = db.courseDao

    override suspend fun insertCourse(
        id: Int,
        price: String,
        publishDate: LocalDate,
        rate: String,
        startDate: LocalDate,
        text: String,
        title: String
    ): ResultWork<Unit, DataError.LocalError> {
        return withContext(Dispatchers.IO) {
            try {
                dao.insertCourse(
                    CourseEntity(
                        id = id,
                        price = price,
                        publishDate = publishDate.toString(),
                        rate = rate,
                        startDate = startDate.toString(),
                        text = text,
                        title = title
                    )
                )
                ResultWork.Success(Unit)
            } catch (e: Exception) {
                e.printStackTrace()
                ResultWork.Error(DataError.LocalError.WRITE_ERROR)
            }
        }
    }

    override suspend fun getAllCourses(): Flow<ResultWork<List<Course>, DataError.LocalError>> {
        return flow {
            try {
                dao.getAllCourses().map {
                    it.map { course ->
                        Course(
                            id = course.id,
                            price = course.price,
                            publishDate = convertStringToLocalDate(course.publishDate),
                            rate = course.rate,
                            startDate = convertStringToLocalDate(course.startDate),
                            text = course.text,
                            title = course.title
                        )
                    }
                }.collect { collector ->
                    emit(
                        ResultWork.Success(collector)
                    )

                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ResultWork.Error(DataError.LocalError.WRITE_ERROR))
            }
        }
    }

    override suspend fun deleteCourse(
        id: Int,
    ): ResultWork<Unit, DataError.LocalError> {
        return withContext(Dispatchers.IO) {
            try {
                dao.deleteCourseById(id)
                ResultWork.Success(Unit)
            } catch (e: Exception) {
                e.printStackTrace()
                ResultWork.Error(DataError.LocalError.WRITE_ERROR)
            }
        }
    }
}