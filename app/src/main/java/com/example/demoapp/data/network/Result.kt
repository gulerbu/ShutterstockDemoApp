package com.example.demoapp.data.network

import androidx.annotation.NonNull

data class Result<T> constructor(val status: Status, val data: T?, val error: Throwable? = null) {

    companion object {

        fun <T> success(@NonNull data: T): Result<T> {
            return Result(Status.SUCCESS, data)
        }

        fun <T> error(throwable: Throwable): Result<T> {
            return Result(
                status = Status.ERROR,
                data = null,
                error = throwable
            )
        }
    }



}