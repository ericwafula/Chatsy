package com.example.domain.helpers

import com.example.domain.helpers.LocalError

sealed interface LocalResult<out T, out E : LocalError> {
    data class Success<out T>(
        val data: T,
    ) : LocalResult<T, Nothing>

    data class Error<out E : DataError>(
        val error: E,
    ) : LocalResult<Nothing, E>
}

inline fun <T, E : DataError, R> LocalResult<T, E>.map(map: (T) -> R): LocalResult<R, E> =
    when (this) {
        is LocalResult.Error -> LocalResult.Error(error)
        is LocalResult.Success -> LocalResult.Success(map(data))
    }

fun <T, E : DataError> LocalResult<T, E>.asEmptyDataResult(): EmptyResult<E> = map { }

typealias EmptyResult<E> = LocalResult<Unit, E>

inline fun <T, E : LocalError> LocalResult<T, E>.onSuccess(block: (T) -> Unit) {
    if (this is LocalResult.Success) {
        block(data)
    }
}

inline fun <T, E : DataError> LocalResult<T, E>.onError(block: (DataError) -> Unit) {
    if (this is LocalResult.Error) {
        block(error)
    }
}
