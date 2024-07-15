package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.fitflextv.data.remote.exception.CreateRemoteUserDetailException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteUserDetailException
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateRemoteUserDetailException
import kotlin.jvm.Throws

interface IUserRemoteDataSource {

    @Throws(CreateRemoteUserDetailException::class)
    suspend fun create(data: CreateUserDTO): UserResponseDTO

    @Throws(UpdateRemoteUserDetailException::class)
    suspend fun update(data: UpdatedUserRequestDTO): UserResponseDTO

    @Throws(FetchRemoteUserDetailException::class)
    suspend fun getDetailById(id: String): UserResponseDTO
}