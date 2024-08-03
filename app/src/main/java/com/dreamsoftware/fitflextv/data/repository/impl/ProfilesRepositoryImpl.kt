package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.preferences.datasource.IProfileSessionDataSource
import com.dreamsoftware.fitflextv.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.fitflextv.data.remote.datasource.IProfilesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IUserRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.PinVerificationRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.fitflextv.data.remote.exception.CreateRemoteProfileExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.DeleteRemoteProfileExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteCategoriesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteProfileByIdExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateRemoteProfileExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.VerifyRemoteProfileExceptionRemote
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.CreateProfileException
import com.dreamsoftware.fitflextv.domain.exception.DeleteProfileException
import com.dreamsoftware.fitflextv.domain.exception.FetchCategoriesException
import com.dreamsoftware.fitflextv.domain.exception.FetchProfilesByUserException
import com.dreamsoftware.fitflextv.domain.exception.GetProfileByIdException
import com.dreamsoftware.fitflextv.domain.exception.GetProfileSelectedException
import com.dreamsoftware.fitflextv.domain.exception.SelectProfileException
import com.dreamsoftware.fitflextv.domain.exception.UpdateProfileException
import com.dreamsoftware.fitflextv.domain.exception.VerifyPinException
import com.dreamsoftware.fitflextv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.fitflextv.domain.repository.IProfilesRepository
import com.dreamsoftware.fitflextv.utils.IMapper
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class ProfilesRepositoryImpl(
    private val profilesRemoteDataSource: IProfilesRemoteDataSource,
    private val userRemoteDataSource: IUserRemoteDataSource,
    private val profilesMapper: IOneSideMapper<ProfileDTO, ProfileBO>,
    private val createProfileMapper: IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO>,
    private val updateProfileMapper: IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO>,
    private val profileSessionMapper: IMapper<ProfileBO, ProfileSelectedPreferenceDTO>,
    private val profileSessionDataSource: IProfileSessionDataSource,
    dispatcher: CoroutineDispatcher
): SupportRepositoryImpl(dispatcher), IProfilesRepository {

    @Throws(FetchProfilesByUserException::class)
    override suspend fun getProfilesByUser(userId: String): List<ProfileBO> = safeExecute {
        try {
            profilesRemoteDataSource
                .getProfilesByUser(userId)
                .let(profilesMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchRemoteCategoriesExceptionRemote) {
            throw FetchCategoriesException("An error occurred when fetching profiles", ex)
        }
    }

    @Throws(UpdateProfileException::class)
    override suspend fun updateProfile(
        profileId: String,
        data: UpdatedProfileRequestBO
    ): ProfileBO = safeExecute {
        try {
            profilesRemoteDataSource
                .updateProfile(profileId, updateProfileMapper.mapInToOut(data))
                .let(profilesMapper::mapInToOut)
        } catch (ex: UpdateRemoteProfileExceptionRemote) {
            throw UpdateProfileException("An error occurred when updating profile", ex)
        }
    }

    @Throws(DeleteProfileException::class)
    override suspend fun deleteProfile(profileId: String): Boolean = safeExecute {
        try {
            with(profilesRemoteDataSource) {
                val profile = getProfileById(profileId)
                deleteProfile(profileId).also {
                    userRemoteDataSource.decrementProfilesCount(profile.userId)
                }
            }
        } catch (ex: DeleteRemoteProfileExceptionRemote) {
            throw DeleteProfileException("An error occurred when deleting profile", ex)
        }
    }

    @Throws(CreateProfileException::class)
    override suspend fun createProfile(data: CreateProfileRequestBO): Boolean = safeExecute {
        try {
            profilesRemoteDataSource
                .createProfile(createProfileMapper.mapInToOut(data)).also {
                    userRemoteDataSource.incrementProfilesCount(data.userId)
                }
        } catch (ex: CreateRemoteProfileExceptionRemote) {
            throw CreateProfileException("An error occurred when creating profiles", ex)
        }
    }

    @Throws(SelectProfileException::class)
    override suspend fun selectProfile(profile: ProfileBO) : Unit = safeExecute {
        profileSessionDataSource.save(profileSessionMapper.mapInToOut(profile))
    }

    @Throws(VerifyPinException::class)
    override suspend fun verifyPin(profileId: String, pin: Int): Unit = safeExecute {
        try {
            profilesRemoteDataSource
                .verifyPin(profileId, PinVerificationRequestDTO(pin)).let { isSuccess ->
                    if(!isSuccess) {
                        throw VerifyPinException("Pin verification failed for profile $profileId")
                    }
                }
        } catch (ex: VerifyRemoteProfileExceptionRemote) {
            throw VerifyPinException("An error occurred when verifying pin profile", ex)
        }
    }

    @Throws(GetProfileByIdException::class)
    override suspend fun getProfileById(profileId: String): ProfileBO = safeExecute {
        try {
            profilesRemoteDataSource
                .getProfileById(profileId)
                .let(profilesMapper::mapInToOut)
        } catch (ex: FetchRemoteProfileByIdExceptionRemote) {
            throw GetProfileByIdException("An error occurred when getting profile by id", ex)
        }
    }

    @Throws(GetProfileSelectedException::class)
    override suspend fun getProfileSelectedByUser(userId: String): ProfileBO = safeExecute {
        profileSessionMapper.mapOutToIn(profileSessionDataSource.get())
    }
}