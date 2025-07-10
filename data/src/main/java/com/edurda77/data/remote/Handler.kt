package com.edurda77.data.remote


import com.edurda77.domain.utils.DataError
import com.edurda77.domain.utils.ResultWork
import retrofit2.HttpException
import java.net.UnknownHostException

suspend fun <D> handleResponse(data: suspend () -> D): ResultWork<D, DataError.RemoteError> {
    return try {
        ResultWork.Success(data())

    }
    catch (e: HttpException) {
        when (e.code()) {
            in 400..499 -> ResultWork.Error(DataError.RemoteError.CLIENT_ERROR)
            in 500..599 ->  ResultWork.Error(DataError.RemoteError.SERVER_ERROR)
            else -> ResultWork.Error(DataError.RemoteError.UNKNOWN)
        }
    }
    catch (e: UnknownHostException) {
        e.printStackTrace()
        ResultWork.Error(DataError.RemoteError.INTERNET_UNVAILABLE)
    }
    catch (e: Exception) {
        e.printStackTrace()
        ResultWork.Error(DataError.RemoteError.UNKNOWN)
    }
}
