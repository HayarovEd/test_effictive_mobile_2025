package com.edurda77.resources.uikit

import com.edurda77.domain.utils.DataError
import com.edurda77.domain.utils.ResultWork
import com.edurda77.resources.R
import com.edurda77.resources.uikit.UiText.StringResource

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.LocalError.READ_ERROR ->
            StringResource(
                R.string.read_error
            )

        DataError.LocalError.WRITE_ERROR -> StringResource(
            R.string.write_error
        )
        DataError.RemoteError.CLIENT_ERROR -> StringResource(
            R.string.bad_request
        )
        DataError.RemoteError.SERVER_ERROR -> StringResource(
            R.string.server_error
        )
        DataError.RemoteError.INTERNET_UNVAILABLE -> StringResource(
            R.string.no_internet
        )
        DataError.RemoteError.UNKNOWN -> StringResource(
            R.string.unknown_error
        )
    }
}

fun ResultWork.Error<*, DataError>.asErrorUiText(): UiText {
    return error.asUiText()
}