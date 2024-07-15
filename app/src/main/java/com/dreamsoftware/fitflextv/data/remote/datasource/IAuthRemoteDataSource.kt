package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.SignInUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.SignUpUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.AuthUserDTO
import com.dreamsoftware.fitflextv.data.remote.exception.AuthException
import com.dreamsoftware.fitflextv.data.remote.exception.SignInException
import com.dreamsoftware.fitflextv.data.remote.exception.SignUpException

/**
 * Interface for authentication data source.
 * Provides methods for user authentication, user retrieval, and session management.
 */
interface IAuthRemoteDataSource {


    @Throws(AuthException::class)
    suspend fun getCurrentAuthenticatedUser(): AuthUserDTO

    /**
     * Gets the UID of the authenticated user.
     * @return the UID of the authenticated user.
     * @throws AuthException if an error occurs or no user is authenticated.
     */
    @Throws(AuthException::class)
    suspend fun getUserAuthenticatedUid(): String

    @Throws(SignInException::class)
    suspend fun signIn(signInUserDTO: SignInUserDTO): AuthUserDTO

    @Throws(SignUpException::class)
    suspend fun signUp(signUpUserDTO: SignUpUserDTO): AuthUserDTO

    /**
     * Signs out the currently authenticated user.
     * @throws AuthException if an error occurs during the sign-out process.
     */
    @Throws(AuthException::class)
    suspend fun closeSession()
}