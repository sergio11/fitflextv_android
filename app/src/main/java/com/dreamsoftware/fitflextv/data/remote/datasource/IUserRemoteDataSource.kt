package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.fitflextv.data.remote.exception.CreateUserDetailRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchUserDetailRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateProfilesCountRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateUserDetailRemoteException
import kotlin.jvm.Throws

interface IUserRemoteDataSource {

    @Throws(CreateUserDetailRemoteException::class)
    suspend fun create(data: CreateUserDTO): UserResponseDTO

    @Throws(UpdateUserDetailRemoteException::class)
    suspend fun update(data: UpdatedUserRequestDTO): UserResponseDTO

    @Throws(FetchUserDetailRemoteException::class)
    suspend fun getDetailById(id: String): UserResponseDTO

    @Throws(UpdateProfilesCountRemoteException::class)
    suspend fun updateProfilesCount(userId: String, profilesCount: Int): UserResponseDTO

    @Throws(UpdateProfilesCountRemoteException::class)
    suspend fun incrementProfilesCount(userId: String): UserResponseDTO

    @Throws(UpdateProfilesCountRemoteException::class)
    suspend fun decrementProfilesCount(userId: String): UserResponseDTO
}