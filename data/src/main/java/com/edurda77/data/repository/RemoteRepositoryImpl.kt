package com.edurda77.data.repository

import com.edurda77.data.remote.TestApi
import com.edurda77.data.remote.handleResponse
import com.edurda77.domain.model.Course
import com.edurda77.domain.repository.RemoteRepository
import com.edurda77.domain.utils.DataError
import com.edurda77.domain.utils.ResultWork
import com.edurda77.domain.utils.convertStringToLocalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepositoryImpl(
    private val api: TestApi
) : RemoteRepository {

    override suspend fun getCourses(): ResultWork<List<Course>, DataError.RemoteError> {
        return withContext(Dispatchers.IO) {
            handleResponse {
                val result = api.getCourses()
                result.courses.map {
                    Course(
                        id = it.id,
                        price = it.price,
                        publishDate = convertStringToLocalDate(it.publishDate),
                        rate = it.rate,
                        startDate = convertStringToLocalDate(it.startDate),
                        text = it.text,
                        title = it.title
                    )
                }
            }
        }
    }
}