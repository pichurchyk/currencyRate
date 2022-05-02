package com.pichurchyk.domain.model

sealed class TaskResult<T> {
    data class Success<T>(val value: T) : TaskResult<T>()
    data class Error<T>(val throwable: Throwable) : TaskResult<T>()
}