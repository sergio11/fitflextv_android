package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IInstructorsRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.response.InstructorDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchInstructorByIdRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchInstructorsRemoteException
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class InstructorsRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val instructorsMapper: IOneSideMapper<Map<String, Any?>, InstructorDTO>,
    dispatcher: CoroutineDispatcher
) : SupportFireStoreDataSourceImpl(dispatcher), IInstructorsRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "instructors"
    }

    @Throws(FetchInstructorsRemoteException::class)
    override suspend fun getInstructors(): List<InstructorDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { instructorsMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchInstructorsRemoteException(
            "An error occurred when trying to fetch instructors",
            ex
        )
    }

    @Throws(FetchInstructorByIdRemoteException::class)
    override suspend fun getInstructorById(id: String): InstructorDTO = try {
        fetchSingleFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .document(id)
                    .get()
            },
            mapper = { instructorsMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchInstructorByIdRemoteException(
            "An error occurred when trying to fetch instructors",
            ex
        )
    }
}