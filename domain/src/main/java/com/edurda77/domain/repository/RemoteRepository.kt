package com.edurda77.domain.repository

import com.edurda77.domain.model.Course
import com.edurda77.domain.utils.DataError
import com.edurda77.domain.utils.ResultWork

interface RemoteRepository {
    suspend fun onLogin(
        email: String,
        password: String
    ): ResultWork<List<Course>, DataError.RemoteError>
}