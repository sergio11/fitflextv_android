package com.dreamsoftware.fitflextv.data.remote.datasource.impl.core

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

abstract class SupportFireStoreDataSourceImpl(
    private val dispatcher: CoroutineDispatcher
) {

    // Helper method to fetch a list of documents from FireStore
    suspend fun <T> fetchListFromFireStore(
        query: () -> Task<QuerySnapshot>,
        mapper: (Map<String, Any?>) -> T
    ): List<T> = withContext(dispatcher) {
        try {
            val snapshot = query().await()
            if (snapshot.isEmpty) {
                throw Exception("No documents found")
            }
            snapshot.documents.map { document ->
                mapper(document.data ?: throw Exception("Document data is null"))
            }
        } catch (ex: Exception) {
            throw Exception("An error occurred when trying to fetch documents", ex)
        }
    }

    // Helper method to fetch a single document from FireStore
    suspend fun <T> fetchSingleFromFireStore(
        query: () -> Task<DocumentSnapshot>,
        mapper: (Map<String, Any?>) -> T
    ): T = withContext(dispatcher) {
        try {
            val document = query().await()
            mapper(document.data ?: throw Exception("Document data is null"))
        } catch (ex: Exception) {
            throw Exception("An error occurred when trying to fetch the document", ex)
        }
    }
}