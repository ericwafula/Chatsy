package com.example.datasource.local.preferences.helpers

import com.example.domain.helpers.DataError
import com.example.domain.helpers.LocalResult
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

// todo handle context switching. This could be a blocking call that might block the main thread
suspend inline fun <T> safeTransaction(
    context: CoroutineContext = EmptyCoroutineContext,
    crossinline block: suspend () -> T,
): LocalResult<T, DataError.Local> =
    try {
        val result = block()
        LocalResult.Success(result)
    } catch (e: Exception) {
        LocalResult.Error(DataError.Local.DISK_FULL)
    }
