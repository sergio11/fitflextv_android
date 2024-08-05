package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.SignInUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.SignUpUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.AuthUserDTO
import com.dreamsoftware.fitflextv.data.remote.exception.AuthRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.SignInRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.SignUpRemoteException

/**
 * Interface for authentication data source.
 * Provides methods for user authentication, user retrieval, and session management.
 */
interface IAuthRemoteDataSource {


    @Throws(AuthRemoteException::class)
    suspend fun hasActiveSession(): Boolean

    /**
     * Gets the UID of the authenticated user.
     * @return the UID of the authenticated user.
     * @throws AuthRemoteException if an error occurs or no user is authenticated.
     */
    @Throws(AuthRemoteException::class)
    suspend fun getUserAuthenticatedUid(): String

    @Throws(SignInRemoteException::class)
    suspend fun signIn(signInUserDTO: SignInUserDTO): AuthUserDTO

    @Throws(SignUpRemoteException::class)
    suspend fun signUp(signUpUserDTO: SignUpUserDTO): AuthUserDTO

    /**
     * Signs out the currently authenticated user.
     * @throws AuthRemoteException if an error occurs during the sign-out process.
     */
    @Throws(AuthRemoteException::class)
    suspend fun closeSession()
}