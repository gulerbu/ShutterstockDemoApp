package com.example.demoapp.data.network

import kotlin.Exception

class ResponseHandler {

    fun <T: Any> handleSuccess(data: T): Result<T> = Result.success(data)

    fun <T: Any> handleException(exception: Exception): Result<T> = Result.error(exception)

}