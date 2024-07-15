package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.SignInUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.SignUpUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.AuthUserDTO
import com.dreamsoftware.fitflextv.data.remote.exception.AuthExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.SignInExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.SignUpExceptionRemote

/**
 * Interface for authentication data source.
 * Provides methods for user authentication, user retrieval, and session management.
 */
interface IAuthRemoteDataSource {


    @Throws(AuthExceptionRemote::class)
    suspend fun getCurrentAuthenticatedUser(): AuthUserDTO

    /**
     * Gets the UID of the authenticated user.
     * @return the UID of the authenticated user.
     * @throws AuthExceptionRemote if an error occurs or no user is authenticated.
     */
    @Throws(AuthExceptionRemote::class)
    suspend fun getUserAuthenticatedUid(): String

    @Throws(SignInExceptionRemote::class)
    suspend fun signIn(signInUserDTO: SignInUserDTO): AuthUserDTO

    @Throws(SignUpExceptionRemote::class)
    suspend fun signUp(signUpUserDTO: SignUpUserDTO): AuthUserDTO

    /**
     * Signs out the currently authenticated user.
     * @throws AuthExceptionRemote if an error occurs during the sign-out process.
     */
    @Throws(AuthExceptionRemote::class)
    suspend fun closeSession()
}