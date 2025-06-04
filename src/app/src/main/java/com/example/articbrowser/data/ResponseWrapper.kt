package com.example.articbrowser.data

data class ResponseWrapper<T>(
    val data: T? = null,
    val exception: Exception? = null
) {
    val isSuccessful: Boolean
        get() = exception == null && data != null
}