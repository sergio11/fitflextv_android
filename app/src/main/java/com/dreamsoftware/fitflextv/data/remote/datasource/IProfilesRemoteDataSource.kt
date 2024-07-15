package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.PinVerificationRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ProfileResponseDTO
import com.dreamsoftware.fitflextv.data.remote.exception.CreateRemoteProfileException
import com.dreamsoftware.fitflextv.data.remote.exception.DeleteRemoteProfileException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteProfilesException
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateRemoteProfileException
import com.dreamsoftware.fitflextv.data.remote.exception.VerifyRemoteProfileException
import kotlin.jvm.Throws

interface IProfilesRemoteDataSource {

    @Throws(FetchRemoteProfilesException::class)
    suspend fun getProfiles(): List<ProfileResponseDTO>

    @Throws(UpdateRemoteProfileException::class)
    suspend fun updateProfile(profileId: String, data: UpdatedProfileRequestDTO): ProfileResponseDTO

    @Throws(DeleteRemoteProfileException::class)
    suspend fun deleteProfile(profileId: String): Boolean

    @Throws(CreateRemoteProfileException::class)
    suspend fun createProfile(data: CreateProfileRequestDTO): Boolean

    @Throws(VerifyRemoteProfileException::class)
    suspend fun verifyPin(profileId: String, data: PinVerificationRequestDTO): Boolean
}