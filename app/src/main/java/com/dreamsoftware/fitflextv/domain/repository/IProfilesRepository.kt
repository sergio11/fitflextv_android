package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.CreateProfileException
import com.dreamsoftware.fitflextv.domain.exception.DeleteProfileException
import com.dreamsoftware.fitflextv.domain.exception.FetchProfilesByUserException
import com.dreamsoftware.fitflextv.domain.exception.GetProfileByIdException
import com.dreamsoftware.fitflextv.domain.exception.GetProfileSelectedException
import com.dreamsoftware.fitflextv.domain.exception.SelectProfileException
import com.dreamsoftware.fitflextv.domain.exception.UpdateProfileException
import com.dreamsoftware.fitflextv.domain.exception.VerifyPinException
import com.dreamsoftware.fitflextv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.model.UpdatedProfileRequestBO

interface IProfilesRepository {

    @Throws(FetchProfilesByUserException::class)
    suspend fun getProfilesByUser(userId: String): List<ProfileBO>

    @Throws(UpdateProfileException::class)
    suspend fun updateProfile(profileId: String, data: UpdatedProfileRequestBO): ProfileBO

    @Throws(DeleteProfileException::class)
    suspend fun deleteProfile(profileId: String): Boolean

    @Throws(CreateProfileException::class)
    suspend fun createProfile(data: CreateProfileRequestBO): Boolean

    @Throws(SelectProfileException::class)
    suspend fun selectProfile(profile: ProfileBO)

    @Throws(VerifyPinException::class)
    suspend fun verifyPin(profileId: String, pin: Int)

    @Throws(GetProfileByIdException::class)
    suspend fun getProfileById(profileId: String): ProfileBO

    @Throws(GetProfileSelectedException::class)
    suspend fun getProfileSelectedByUser(userId: String): ProfileBO
}