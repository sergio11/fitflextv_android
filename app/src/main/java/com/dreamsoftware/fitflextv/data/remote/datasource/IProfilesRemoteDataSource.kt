package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.PinVerificationRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.fitflextv.data.remote.exception.CreateRemoteProfileExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.DeleteRemoteProfileExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteProfileByIdExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteProfilesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateRemoteProfileExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.VerifyRemoteProfileExceptionRemote

interface IProfilesRemoteDataSource {

    @Throws(FetchRemoteProfilesExceptionRemote::class)
    suspend fun getProfilesByUser(userId: String): List<ProfileDTO>

    @Throws(UpdateRemoteProfileExceptionRemote::class)
    suspend fun updateProfile(profileId: String, data: UpdatedProfileRequestDTO): ProfileDTO

    @Throws(DeleteRemoteProfileExceptionRemote::class)
    suspend fun deleteProfile(profileId: String): Boolean

    @Throws(CreateRemoteProfileExceptionRemote::class)
    suspend fun createProfile(data: CreateProfileRequestDTO): Boolean

    @Throws(VerifyRemoteProfileExceptionRemote::class)
    suspend fun verifyPin(profileId: String, data: PinVerificationRequestDTO): Boolean

    @Throws(FetchRemoteProfileByIdExceptionRemote::class)
    suspend fun getProfileById(profileId: String): ProfileDTO
}