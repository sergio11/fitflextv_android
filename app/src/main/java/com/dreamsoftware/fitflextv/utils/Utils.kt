package com.dreamsoftware.fitflextv.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.enums.enumEntries

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val error: Throwable) : Result<Nothing>
    data object Loading : Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> = map<T, Result<T>> {
    Result.Success(it)
}.onStart {
    emit(Result.Loading)
}.catch {
    emit(Result.Error(it))
}

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Enum<T>> enumNameOfOrDefault(name: String, default: T): T =
    enumEntries<T>().find { it.name == name } ?: default

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Enum<T>> enumOfOrDefault(predicate: (T) -> Boolean, default: T): T =
    enumEntries<T>().find(predicate) ?: default

inline fun <T1 : Any, T2 : Any, R> combinedLet(value1: T1?, value2: T2?, block: (T1, T2) -> R): R? =
    if (value1 != null && value2 != null) {
        block(value1, value2)
    } else {
        null
    }