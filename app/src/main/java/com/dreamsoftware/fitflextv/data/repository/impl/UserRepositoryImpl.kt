package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IAuthRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IUserRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.SignInUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.SignUpUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.fitflextv.data.remote.exception.AuthExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteUserDetailExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.SignInExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateRemoteUserDetailExceptionRemote
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.GetUserDetailException
import com.dreamsoftware.fitflextv.domain.exception.SignInException
import com.dreamsoftware.fitflextv.domain.exception.SignOffException
import com.dreamsoftware.fitflextv.domain.exception.SignUpException
import com.dreamsoftware.fitflextv.domain.exception.UpdateUserDetailException
import com.dreamsoftware.fitflextv.domain.model.CreateUserBO
import com.dreamsoftware.fitflextv.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class UserRepositoryImpl(
    private val userRemoteDataSource: IUserRemoteDataSource,
    private val authRemoteDataSource: IAuthRemoteDataSource,
    private val userDetailMapper: IOneSideMapper<UserResponseDTO, UserDetailBO>,
    private val updatedUserRequestMapper: IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO>,
    private val createUserMapper: IOneSideMapper<CreateUserBO, CreateUserDTO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), IUserRepository {

    @Throws(SignInException::class)
    override suspend fun signIn(email: String, password: String): UserDetailBO = safeExecute {
        try {
            authRemoteDataSource.signIn(SignInUserDTO(email, password))
                .let { userRemoteDataSource.getDetailById(it.uid) }
                .let(userDetailMapper::mapInToOut)
        } catch (ex: SignInExceptionRemote) {
            throw SignInException("An error occurred when trying to sign in user", ex)
        }
    }

    @Throws(SignUpException::class)
    override suspend fun signUp(user: CreateUserBO): UserDetailBO = safeExecute {
        try {
            authRemoteDataSource.signUp(SignUpUserDTO(user.email, user.password))
                .let { userRemoteDataSource.create(createUserMapper.mapInToOut(user).copy(uid = it.uid)) }
                .let(userDetailMapper::mapInToOut)
        } catch (ex: SignInExceptionRemote) {
            throw SignInException("An error occurred when trying to sign in user", ex)
        }
    }

    @Throws(SignOffException::class)
    override suspend fun signOff() = safeExecute {
        try {
            authRemoteDataSource.closeSession()
        } catch (ex: AuthExceptionRemote) {
            throw SignOffException("An error occurred when trying to sign off user", ex)
        }
    }

    @Throws(GetUserDetailException::class)
    override suspend fun getDetail(): UserDetailBO = safeExecute {
        try {
            userRemoteDataSource.getDetailById(authRemoteDataSource.getUserAuthenticatedUid())
                .let(userDetailMapper::mapInToOut)
        } catch (ex: FetchRemoteUserDetailExceptionRemote) {
            throw GetUserDetailException("An error occurred when trying to get user detail", ex)
        }
    }

    @Throws(UpdateUserDetailException::class)
    override suspend fun updateDetail(data: UpdatedUserRequestBO): UserDetailBO = safeExecute {
        try {
            userRemoteDataSource.update(updatedUserRequestMapper.mapInToOut(data))
                .let(userDetailMapper::mapInToOut)
        } catch (ex: UpdateRemoteUserDetailExceptionRemote) {
            throw UpdateUserDetailException("An error occurred when trying to update user detail", ex)
        }
    }
}