package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.fitflextv.data.remote.exception.CreateRemoteUserDetailExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteUserDetailExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateProfilesCountException
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateRemoteUserDetailExceptionRemote
import kotlin.jvm.Throws

interface IUserRemoteDataSource {

    @Throws(CreateRemoteUserDetailExceptionRemote::class)
    suspend fun create(data: CreateUserDTO): UserResponseDTO

    @Throws(UpdateRemoteUserDetailExceptionRemote::class)
    suspend fun update(data: UpdatedUserRequestDTO): UserResponseDTO

    @Throws(FetchRemoteUserDetailExceptionRemote::class)
    suspend fun getDetailById(id: String): UserResponseDTO

    @Throws(UpdateProfilesCountException::class)
    suspend fun updateProfilesCount(userId: String, profilesCount: Int): UserResponseDTO

    @Throws(UpdateProfilesCountException::class)
    suspend fun incrementProfilesCount(userId: String): UserResponseDTO

    @Throws(UpdateProfilesCountException::class)
    suspend fun decrementProfilesCount(userId: String): UserResponseDTO
}