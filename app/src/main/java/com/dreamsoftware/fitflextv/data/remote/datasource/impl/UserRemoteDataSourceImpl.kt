package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IUserRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.fitflextv.data.remote.exception.CreateRemoteUserDetailExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineByIdExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteUserDetailExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateRemoteUserDetailExceptionRemote
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.jvm.Throws

internal class UserRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val usersMapper: IOneSideMapper<Map<String, Any?>, UserResponseDTO>,
    private val updatedUserRequestMapper: IOneSideMapper<UpdatedUserRequestDTO, Map<String, Any?>>,
    private val createUserRequestMapper: IOneSideMapper<CreateUserDTO, Map<String, Any?>>,
    private val dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), IUserRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "users"
    }

    @Throws(CreateRemoteUserDetailExceptionRemote::class)
    override suspend fun create(data: CreateUserDTO): UserResponseDTO = try {
        withContext(dispatcher) {
            val userData = createUserRequestMapper.mapInToOut(data)
            firebaseStore.collection(COLLECTION_NAME)
                .document(data.uid)
                .set(userData)
                .await()
            val document = firebaseStore.collection(COLLECTION_NAME)
                .document(data.uid)
                .get()
                .await()
            usersMapper.mapInToOut(document.data ?: throw CreateRemoteUserDetailExceptionRemote("User data is null"))
        }
    } catch (ex: Exception) {
        throw CreateRemoteUserDetailExceptionRemote("An error occurred when trying to create the user", ex)
    }

    @Throws(UpdateRemoteUserDetailExceptionRemote::class)
    override suspend fun update(data: UpdatedUserRequestDTO): UserResponseDTO = try {
        withContext(dispatcher) {
            val updates = updatedUserRequestMapper.mapInToOut(data)
            val uid = updates["uid"] as? String ?: throw UpdateRemoteUserDetailExceptionRemote("User ID is required")
            firebaseStore.collection(COLLECTION_NAME)
                .document(uid)
                .update(updates)
                .await()
            val document = firebaseStore.collection(COLLECTION_NAME)
                .document(uid)
                .get()
                .await()
            usersMapper.mapInToOut(document.data ?: throw UpdateRemoteUserDetailExceptionRemote("User data is null"))
        }
    } catch (ex: Exception) {
        throw UpdateRemoteUserDetailExceptionRemote("An error occurred when trying to update the user", ex)
    }

    @Throws(FetchRemoteUserDetailExceptionRemote::class)
    override suspend fun getDetailById(id: String): UserResponseDTO = try {
        fetchSingleFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME)
                .document(id)
                .get() },
            mapper = { usersMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteRoutineByIdExceptionRemote("An error occurred when trying to fetch the users with ID $id", ex)
    }
}