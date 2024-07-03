package com.dreamsoftware.fitflextv.domain.usecase.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A base class for asynchronous use cases that do not require parameters and return a result.
 *
 * @param ReturnType The type of the result returned by the use case.
 */
abstract class BaseUseCase<ReturnType> {

    /**
     * This function must be implemented in the child class to provide the actual
     * execution logic of the use case.
     *
     * @return The result of executing the use case.
     */
    abstract suspend fun onExecuted(): ReturnType

    /**
     * Invokes the use case synchronously within the provided coroutine scope.
     *
     * @param scope The coroutine scope in which to execute the use case.
     * @return The result of executing the use case.
     */
    suspend operator fun invoke(
        scope: CoroutineScope
    ): ReturnType = withContext(scope.coroutineContext) { onExecuted() }

    /**
     * Invokes the use case asynchronously within the provided coroutine scope,
     * providing hooks for actions on success and on error.
     *
     * @param scope The coroutine scope in which to execute the use case.
     * @param onSuccess A lambda function to be executed when the use case completes successfully.
     * @param onError A lambda function to be executed if the use case encounters an error.
     */
    operator fun invoke(
        scope: CoroutineScope,
        onSuccess: (result: ReturnType) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        val backgroundJob = scope.async { onExecuted() }
        scope.launch {
            try {
                onSuccess(backgroundJob.await())
            } catch (ex: Exception) {
                ex.printStackTrace()
                onError(ex)
            }
        }
    }
}