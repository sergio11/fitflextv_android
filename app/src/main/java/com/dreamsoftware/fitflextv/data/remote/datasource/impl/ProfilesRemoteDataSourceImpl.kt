package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IProfilesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.PinVerificationRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ProfileResponseDTO
import com.dreamsoftware.fitflextv.data.remote.exception.CreateRemoteProfileException
import com.dreamsoftware.fitflextv.data.remote.exception.DeleteRemoteProfileException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteProfilesException
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateRemoteProfileException
import com.dreamsoftware.fitflextv.data.remote.exception.VerifyRemoteProfileException
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.jvm.Throws

internal class ProfilesRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val profilesMapper: IOneSideMapper<Map<String, Any?>, ProfileResponseDTO>,
    private val createProfileRequestMapper: IOneSideMapper<CreateProfileRequestDTO, Map<String, Any?>>,
    private val updateProfileRequestMapper: IOneSideMapper<UpdatedProfileRequestDTO, Map<String, Any?>>,
    private val dispatcher: CoroutineDispatcher
) : SupportFireStoreDataSourceImpl(dispatcher), IProfilesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "profiles"
    }

    @Throws(FetchRemoteProfilesException::class)
    override suspend fun getProfiles(): List<ProfileResponseDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { data -> profilesMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRemoteChallengesException("An error occurred when trying to fetch profiles", ex)
    }

    @Throws(UpdateRemoteProfileException::class)
    override suspend fun updateProfile(
        profileId: String,
        data: UpdatedProfileRequestDTO
    ): ProfileResponseDTO = try {
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
                updatedDoc.data ?: throw UpdateRemoteProfileException("Profile data is null")
            )
        }
    } catch (ex: Exception) {
        throw UpdateRemoteProfileException(
            "An error occurred when trying to update the profile with ID $profileId",
            ex
        )
    }

    @Throws(DeleteRemoteProfileException::class)
    override suspend fun deleteProfile(profileId: String): Boolean = try {
        withContext(dispatcher) {
            firebaseStore.collection(COLLECTION_NAME)
                .document(profileId)
                .delete()
                .await()
            true
        }
    } catch (ex: Exception) {
        throw DeleteRemoteProfileException(
            "An error occurred when trying to delete the profile with ID $profileId",
            ex
        )
    }

    @Throws(CreateRemoteProfileException::class)
    override suspend fun createProfile(data: CreateProfileRequestDTO): Boolean = try {
        withContext(dispatcher) {
            firebaseStore.collection(COLLECTION_NAME)
                .document(data.uid)
                .set(createProfileRequestMapper.mapInToOut(data))
                .await()
            true
        }
    } catch (ex: Exception) {
        throw CreateRemoteProfileException(
            "An error occurred when trying to create a new profile",
            ex
        )
    }

    @Throws(VerifyRemoteProfileException::class)
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
            throw VerifyRemoteProfileException(
                "An error occurred when trying to verify the pin for profile with ID $profileId",
                ex
            )
        }
}