package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.ISessionRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.SessionDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteSessionsException
import com.dreamsoftware.fitflextv.data.remote.exception.DataSourceException
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

internal class SessionRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val sessionsMapper: IOneSideMapper<Map<String, Any?>, SessionDTO>,
    private val dispatcher: CoroutineDispatcher
): ISessionRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "sessions"
    }

    @Throws(FetchRemoteSessionsException::class)
    override suspend fun getSessions(): List<SessionDTO> = withContext(dispatcher)  {
        try {
            val snapshot = firebaseStore.collection(COLLECTION_NAME)
                .get()
                .await()
            snapshot.documents.map { document ->
                sessionsMapper.mapInToOut(
                    document.data ?: throw FetchRemoteSessionsException("sessions data is null")
                )
            }
        } catch (ex: DataSourceException) {
            throw ex
        } catch (ex: Exception) {
            throw FetchRemoteSessionsException(
                "An error occurred when trying to fetch sessions",
                ex
            )
        }
    }
}