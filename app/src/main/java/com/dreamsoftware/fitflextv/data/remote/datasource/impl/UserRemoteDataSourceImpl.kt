package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IUserRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.fitflextv.data.remote.exception.CreateUserDetailRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchUserDetailRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateProfilesCountRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateUserDetailRemoteException
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

internal class UserRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val usersMapper: IOneSideMapper<Map<String, Any?>, UserResponseDTO>,
    private val updatedUserRequestMapper: IOneSideMapper<UpdatedUserRequestDTO, Map<String, Any?>>,
    private val createUserRequestMapper: IOneSideMapper<CreateUserDTO, Map<String, Any?>>,
    private val dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), IUserRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "users"
        const val PROFILES_COUNT_FIELD = "profilesCount"
    }

    @Throws(CreateUserDetailRemoteException::class)
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
            usersMapper.mapInToOut(document.data ?: throw CreateUserDetailRemoteException("User data is null"))
        }
    } catch (ex: Exception) {
        throw CreateUserDetailRemoteException("An error occurred when trying to create the user", ex)
    }

    @Throws(UpdateUserDetailRemoteException::class)
    override suspend fun update(data: UpdatedUserRequestDTO): UserResponseDTO = try {
        withContext(dispatcher) {
            val updates = updatedUserRequestMapper.mapInToOut(data)
            val uid = updates["uid"] as? String ?: throw UpdateUserDetailRemoteException("User ID is required")
            firebaseStore.collection(COLLECTION_NAME)
                .document(uid)
                .update(updates)
                .await()
            val document = firebaseStore.collection(COLLECTION_NAME)
                .document(uid)
                .get()
                .await()
            usersMapper.mapInToOut(document.data ?: throw UpdateUserDetailRemoteException("User data is null"))
        }
    } catch (ex: Exception) {
        throw UpdateUserDetailRemoteException("An error occurred when trying to update the user", ex)
    }

    @Throws(FetchUserDetailRemoteException::class)
    override suspend fun getDetailById(id: String): UserResponseDTO = try {
        fetchSingleFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME)
                .document(id)
                .get() },
            mapper = { usersMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchUserDetailRemoteException("An error occurred when trying to fetch the users with ID $id", ex)
    }

    @Throws(UpdateProfilesCountRemoteException::class)
    override suspend fun updateProfilesCount(userId: String, profilesCount: Int): UserResponseDTO = withContext(dispatcher) {
        try {
            val document = firebaseStore.collection(COLLECTION_NAME).document(userId)
            document.update(PROFILES_COUNT_FIELD, profilesCount).await()
            getDetailById(userId)
        } catch (ex: Exception) {
            throw UpdateProfilesCountRemoteException("An error occurred when trying to update profiles count", ex)
        }
    }

    @Throws(UpdateProfilesCountRemoteException::class)
    override suspend fun incrementProfilesCount(userId: String): UserResponseDTO = withContext(dispatcher) {
        try {
            val document = firebaseStore.collection(COLLECTION_NAME).document(userId)
            document.update(PROFILES_COUNT_FIELD, FieldValue.increment(1)).await()
            getDetailById(userId)
        } catch (ex: Exception) {
            throw UpdateProfilesCountRemoteException("An error occurred when trying to increment profiles count", ex)
        }
    }

    @Throws(UpdateProfilesCountRemoteException::class)
    override suspend fun decrementProfilesCount(userId: String): UserResponseDTO = withContext(dispatcher) {
        try {
            val document = firebaseStore.collection(COLLECTION_NAME).document(userId)
            document.update(PROFILES_COUNT_FIELD, FieldValue.increment(-1)).await()
            getDetailById(userId)
        } catch (ex: Exception) {
            throw UpdateProfilesCountRemoteException("An error occurred when trying to decrement profiles count", ex)
        }
    }
}