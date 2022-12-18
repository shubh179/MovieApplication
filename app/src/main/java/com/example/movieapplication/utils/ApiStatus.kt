package com.example.movieapplication.utils

data class ApiStatus<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {

        fun <T> success(data: T?): ApiStatus<T> {
            return ApiStatus(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): ApiStatus<T> {
            return ApiStatus(Status.ERROR, null, msg)
        }

        fun <T> loading(): ApiStatus<T> {
            return ApiStatus(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}