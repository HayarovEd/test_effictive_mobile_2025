package com.edurda77.domain.utils

sealed interface DataError : RootError {
    enum class RemoteError: DataError {
        CLIENT_ERROR,
        SERVER_ERROR,
        INTERNET_UNVAILABLE,
        UNKNOWN
    }
    enum class LocalError: DataError {
        READ_ERROR,
        WRITE_ERROR,
    }
}