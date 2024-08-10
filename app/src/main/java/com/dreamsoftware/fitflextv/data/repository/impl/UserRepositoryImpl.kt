package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.preferences.datasource.IUserPreferencesDataSource
import com.dreamsoftware.fitflextv.data.preferences.dto.UserPreferencesDTO
import com.dreamsoftware.fitflextv.data.preferences.exception.FetchUserPreferencesLocalException
import com.dreamsoftware.fitflextv.data.preferences.exception.SaveUserPreferencesLocalException
import com.dreamsoftware.fitflextv.data.remote.datasource.IAuthRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.datasource.IUserRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.SignInUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.SignUpUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.fitflextv.data.remote.exception.AuthRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchUserDetailRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.SignInRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.UpdateUserDetailRemoteException
import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.exception.GetUserAuthenticatedUidException
import com.dreamsoftware.fitflextv.domain.exception.GetUserDetailException
import com.dreamsoftware.fitflextv.domain.exception.GetUserPreferencesException
import com.dreamsoftware.fitflextv.domain.exception.SaveUserPreferencesException
import com.dreamsoftware.fitflextv.domain.exception.SignInException
import com.dreamsoftware.fitflextv.domain.exception.SignOffException
import com.dreamsoftware.fitflextv.domain.exception.SignUpException
import com.dreamsoftware.fitflextv.domain.exception.UpdateUserDetailException
import com.dreamsoftware.fitflextv.domain.exception.VerifySessionException
import com.dreamsoftware.fitflextv.domain.model.SignInBO
import com.dreamsoftware.fitflextv.domain.model.SignUpBO
import com.dreamsoftware.fitflextv.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.model.UserPreferenceBO
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.utils.IMapper
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class UserRepositoryImpl(
    private val userRemoteDataSource: IUserRemoteDataSource,
    private val authRemoteDataSource: IAuthRemoteDataSource,
    private val userPreferencesDataSource: IUserPreferencesDataSource,
    private val userDetailMapper: IOneSideMapper<UserResponseDTO, UserDetailBO>,
    private val userPreferencesMapper: IMapper<UserPreferencesDTO, UserPreferenceBO>,
    private val updatedUserRequestMapper: IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO>,
    private val createUserMapper: IOneSideMapper<SignUpBO, CreateUserDTO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), IUserRepository {

    @Throws(SignInException::class)
    override suspend fun signIn(data: SignInBO): UserDetailBO = safeExecute {
        try {
            authRemoteDataSource.signIn(SignInUserDTO(data.email, data.password))
                .let { userRemoteDataSource.getDetailById(it.uid) }
                .let(userDetailMapper::mapInToOut)
        } catch (ex: SignInRemoteException) {
            throw SignInException("An error occurred when trying to sign in user", ex)
        }
    }

    @Throws(SignUpException::class)
    override suspend fun signUp(user: SignUpBO): UserDetailBO = safeExecute {
        try {
            authRemoteDataSource.signUp(SignUpUserDTO(user.email, user.password))
                .let { userRemoteDataSource.create(createUserMapper.mapInToOut(user).copy(uid = it.uid)) }
                .let(userDetailMapper::mapInToOut)
        } catch (ex: SignInRemoteException) {
            throw SignInException("An error occurred when trying to sign in user", ex)
        }
    }

    @Throws(SignOffException::class)
    override suspend fun signOff() = safeExecute {
        try {
            authRemoteDataSource.closeSession()
        } catch (ex: AuthRemoteException) {
            throw SignOffException("An error occurred when trying to sign off user", ex)
        }
    }

    @Throws(VerifySessionException::class)
    override suspend fun hasSession(): Boolean = safeExecute {
        try {
            authRemoteDataSource.hasActiveSession()
        } catch (ex: AuthRemoteException) {
            throw SignOffException("An error occurred when trying to check auth state", ex)
        }
    }

    @Throws(GetUserDetailException::class)
    override suspend fun getDetail(): UserDetailBO = safeExecute {
        try {
            userRemoteDataSource.getDetailById(authRemoteDataSource.getUserAuthenticatedUid())
                .let(userDetailMapper::mapInToOut)
        } catch (ex: FetchUserDetailRemoteException) {
            throw GetUserDetailException("An error occurred when trying to get user detail", ex)
        }
    }

    @Throws(GetUserAuthenticatedUidException::class)
    override suspend fun getAuthenticatedUid(): String = safeExecute {
        try {
            authRemoteDataSource.getUserAuthenticatedUid()
        } catch (ex: AuthRemoteException) {
            throw GetUserAuthenticatedUidException("An error occurred when trying to get user auth uid", ex)
        }
    }

    @Throws(UpdateUserDetailException::class)
    override suspend fun updateDetail(data: UpdatedUserRequestBO): UserDetailBO = safeExecute {
        try {
            userRemoteDataSource.update(updatedUserRequestMapper.mapInToOut(data))
                .let(userDetailMapper::mapInToOut)
        } catch (ex: UpdateUserDetailRemoteException) {
            throw UpdateUserDetailException("An error occurred when trying to update user detail", ex)
        }
    }

    @Throws(SaveUserPreferencesException::class)
    override suspend fun saveUserPreferences(data: UserPreferenceBO): Unit = safeExecute {
        try {
            userPreferencesDataSource.save(userPreferencesMapper.mapOutToIn(data))
        } catch (ex: SaveUserPreferencesLocalException) {
            throw SaveUserPreferencesException("An error occurred when trying to save user preferences", ex)
        }
    }

    @Throws(GetUserPreferencesException::class)
    override suspend fun getUserPreferences(): UserPreferenceBO = safeExecute {
        try {
            userPreferencesDataSource.get()
                .let(userPreferencesMapper::mapInToOut)
        } catch (ex: FetchUserPreferencesLocalException) {
            throw GetUserPreferencesException("An error occurred when trying to fetch user preferences", ex)
        }
    }
}