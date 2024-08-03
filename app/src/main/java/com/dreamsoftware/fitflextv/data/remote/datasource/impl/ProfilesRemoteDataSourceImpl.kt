package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IProfilesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.PinVerificationRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.fitflextv.data.remote.exception.CreateRemoteProfileExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.DeleteRemoteProfileExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteProfileByIdExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteProfilesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteRoutineByIdExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateRemoteProfileExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.VerifyRemoteProfileExceptionRemote
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.jvm.Throws

internal class ProfilesRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val profilesMapper: IOneSideMapper<Map<String, Any?>, ProfileDTO>,
    private val createProfileRequestMapper: IOneSideMapper<CreateProfileRequestDTO, Map<String, Any?>>,
    private val updateProfileRequestMapper: IOneSideMapper<UpdatedProfileRequestDTO, Map<String, Any?>>,
    private val dispatcher: CoroutineDispatcher
) : SupportFireStoreDataSourceImpl(dispatcher), IProfilesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "profiles"
        const val USER_ID = "userId"
    }

    @Throws(FetchRemoteProfilesExceptionRemote::class)
    override suspend fun getProfilesByUser(userId: String): List<ProfileDTO> = try {
        fetchListFromFireStore(
            query = {
                firebaseStore
                    .collection(COLLECTION_NAME)
                    .whereEqualTo(USER_ID, userId)
                    .get()
            },
            mapper = { data -> profilesMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteChallengesExceptionRemote("An error occurred when trying to fetch profiles", ex)
    }

    @Throws(UpdateRemoteProfileExceptionRemote::class)
    override suspend fun updateProfile(
        profileId: String,
        data: UpdatedProfileRequestDTO
    ): ProfileDTO = try {
        withContext(dispatcher) {
            firebaseStore.collection(COLLECTION_NAME)
                .document(profileId)
                .update(updateProfileRequestMapper.mapInToOut(data))
                .await()
            val updatedDoc = firebaseStore.collection(COLLECTION_NAME)
                .document(profileId)
                .get()
                .await()
            profilesMapper.mapInToOut(
                updatedDoc.data ?: throw UpdateRemoteProfileExceptionRemote("Profile data is null")
            )
        }
    } catch (ex: Exception) {
        throw UpdateRemoteProfileExceptionRemote(
            "An error occurred when trying to update the profile with ID $profileId",
            ex
        )
    }

    @Throws(DeleteRemoteProfileExceptionRemote::class)
    override suspend fun deleteProfile(profileId: String): Boolean = try {
        withContext(dispatcher) {
            firebaseStore.collection(COLLECTION_NAME)
                .document(profileId)
                .delete()
                .await()
            true
        }
    } catch (ex: Exception) {
        throw DeleteRemoteProfileExceptionRemote(
            "An error occurred when trying to delete the profile with ID $profileId",
            ex
        )
    }

    @Throws(CreateRemoteProfileExceptionRemote::class)
    override suspend fun createProfile(data: CreateProfileRequestDTO): Boolean = try {
        withContext(dispatcher) {
            firebaseStore.collection(COLLECTION_NAME)
                .document(data.uid)
                .set(createProfileRequestMapper.mapInToOut(data))
                .await()
            true
        }
    } catch (ex: Exception) {
        throw CreateRemoteProfileExceptionRemote(
            "An error occurred when trying to create a new profile",
            ex
        )
    }

    @Throws(VerifyRemoteProfileExceptionRemote::class)
    override suspend fun verifyPin(profileId: String, data: PinVerificationRequestDTO): Boolean =
        try {
            withContext(dispatcher) {
                val document = firebaseStore.collection(COLLECTION_NAME)
                    .document(profileId)
                    .get()
                    .await()
                val storedPin = document.getLong("pin")
                storedPin != null && storedPin == data.pin.toLong()
            }
        } catch (ex: Exception) {
            throw VerifyRemoteProfileExceptionRemote(
                "An error occurred when trying to verify the pin for profile with ID $profileId",
                ex
            )
        }

    @Throws(FetchRemoteProfileByIdExceptionRemote::class)
    override suspend fun getProfileById(profileId: String): ProfileDTO = try {
        fetchSingleFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .document(profileId)
                    .get()
            },
            mapper = { profilesMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteRoutineByIdExceptionRemote(
            "An error occurred when trying to fetch the profile with ID $profileId",
            ex
        )
    }
}