package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.PinVerificationRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.fitflextv.data.remote.exception.CreateProfileRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.DeleteProfileRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchProfileByIdRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchProfilesRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateProfileRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.VerifyProfileRemoteException

interface IProfilesRemoteDataSource {

    @Throws(FetchProfilesRemoteException::class)
    suspend fun getProfilesByUser(userId: String): List<ProfileDTO>

    @Throws(UpdateProfileRemoteException::class)
    suspend fun updateProfile(profileId: String, data: UpdatedProfileRequestDTO): ProfileDTO

    @Throws(DeleteProfileRemoteException::class)
    suspend fun deleteProfile(profileId: String): Boolean

    @Throws(CreateProfileRemoteException::class)
    suspend fun createProfile(data: CreateProfileRequestDTO): Boolean

    @Throws(VerifyProfileRemoteException::class)
    suspend fun verifyPin(profileId: String, data: PinVerificationRequestDTO): Boolean

    @Throws(FetchProfileByIdRemoteException::class)
    suspend fun getProfileById(profileId: String): ProfileDTO
}