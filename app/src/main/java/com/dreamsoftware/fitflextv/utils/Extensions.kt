package com.dreamsoftware.fitflextv.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

fun <T> CoroutineScope.executeAsync(dispatcher: CoroutineDispatcher, onExecuted: suspend () -> Iterable<T>): Deferred<Iterable<T>> =
    async(dispatcher) {
        runCatching { onExecuted() }.getOrElse { emptyList() }
    }

suspend fun <T, R> List<T>.parallelMap(
    context: CoroutineContext = Dispatchers.Default,
    block: suspend (T) -> R
): List<R> =
    map { item ->
        coroutineScope {
            async(context) {
                block(item)
            }
        }
    }.awaitAll()