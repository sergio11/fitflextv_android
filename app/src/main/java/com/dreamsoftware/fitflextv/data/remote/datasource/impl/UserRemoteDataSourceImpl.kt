package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IUserRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.fitflextv.data.remote.exception.CreateRemoteUserDetailException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteUserDetailException
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateRemoteUserDetailException
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

    @Throws(CreateRemoteUserDetailException::class)
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
            usersMapper.mapInToOut(document.data ?: throw CreateRemoteUserDetailException("User data is null"))
        }
    } catch (ex: Exception) {
        throw CreateRemoteUserDetailException("An error occurred when trying to create the user", ex)
    }

    @Throws(UpdateRemoteUserDetailException::class)
    override suspend fun update(data: UpdatedUserRequestDTO): UserResponseDTO = try {
        withContext(dispatcher) {
            val updates = updatedUserRequestMapper.mapInToOut(data)
            val uid = updates["uid"] as? String ?: throw UpdateRemoteUserDetailException("User ID is required")
            firebaseStore.collection(COLLECTION_NAME)
                .document(uid)
                .update(updates)
                .await()
            val document = firebaseStore.collection(COLLECTION_NAME)
                .document(uid)
                .get()
                .await()
            usersMapper.mapInToOut(document.data ?: throw UpdateRemoteUserDetailException("User data is null"))
        }
    } catch (ex: Exception) {
        throw UpdateRemoteUserDetailException("An error occurred when trying to update the user", ex)
    }

    @Throws(FetchRemoteUserDetailException::class)
    override suspend fun getDetailById(id: String): UserResponseDTO = try {
        fetchSingleFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME)
                .document(id)
                .get() },
            mapper = { usersMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteRoutineByIdException("An error occurred when trying to fetch the users with ID $id", ex)
    }
}