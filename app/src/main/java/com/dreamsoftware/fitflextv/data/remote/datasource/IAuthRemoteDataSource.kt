package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.AuthUserDTO
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

    /**
     * Signs in a user with the given email and password.
     * @param email the email of the user.
     * @param password the password of the user.
     * @return an AuthUserDTO representing the authenticated user.
     * @throws SignInException if an error occurs during the sign-in process.
     */
    @Throws(SignInException::class)
    suspend fun signIn(email: String, password: String): AuthUserDTO

    /**
     * Signs up a new user with the given email and password.
     * @param email the email of the new user.
     * @param password the password of the new user.
     * @return an AuthUserDTO representing the newly created user.
     * @throws SignUpException if an error occurs during the sign-up process.
     */
    @Throws(SignUpException::class)
    suspend fun signUp(email: String, password: String): AuthUserDTO

    /**
     * Signs out the currently authenticated user.
     * @throws AuthException if an error occurs during the sign-out process.
     */
    @Throws(AuthException::class)
    suspend fun closeSession()
}