package com.dreamsoftware.fitflextv.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

fun <T> CoroutineScope.executeAsync(dispatcher: CoroutineDispatcher, onExecuted: suspend () -> Iterable<T>): Deferred<Iterable<T>> =
    async(dispatcher) {
        runCatching { onExecuted() }.getOrElse { emptyList() }
    }