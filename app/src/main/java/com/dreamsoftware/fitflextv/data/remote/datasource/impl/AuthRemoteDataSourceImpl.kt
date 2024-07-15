package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IAuthRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.request.SignInUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.request.SignUpUserDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.AuthUserDTO
import com.dreamsoftware.fitflextv.data.remote.exception.AuthException
import com.dreamsoftware.fitflextv.data.remote.exception.SignInException
import com.dreamsoftware.fitflextv.data.remote.exception.SignUpException
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

// Implementation of the IAuthDataSource interface
internal class AuthRemoteDataSourceImpl(
    private val userAuthenticatedMapper: IOneSideMapper<FirebaseUser, AuthUserDTO>, // Mapper to convert Firebase user to AuthUserDTO
    private val firebaseAuth: FirebaseAuth // Firebase Authentication instance
) : IAuthRemoteDataSource {


    @Throws(AuthException::class)
    override suspend fun getCurrentAuthenticatedUser(): AuthUserDTO = withContext(Dispatchers.IO) {
        try {
            firebaseAuth.currentUser?.let { userAuthenticatedMapper.mapInToOut(it) } ?: throw IllegalStateException("Auth user cannot be null") // Return true if there is a current authenticated user
        } catch (ex: Exception) {
            throw AuthException("An error occurred when trying to check auth state", ex) // Throw AuthException if an error occurs
        }
    }

    /**
     * Gets the UID of the authenticated user.
     * @return the UID of the authenticated user.
     * @throws AuthException if an error occurs or no user is authenticated.
     */
    @Throws(AuthException::class)
    override suspend fun getUserAuthenticatedUid(): String = withContext(Dispatchers.IO) {
        firebaseAuth.currentUser?.uid ?: throw AuthException("An error occurred when trying to check auth state")
        // Return the UID of the current authenticated user or throw AuthException if no user is authenticated
    }


    @Throws(SignInException::class)
    override suspend fun signIn(signInUserDTO: SignInUserDTO): AuthUserDTO =
        withContext(Dispatchers.IO) {
            try {
                firebaseAuth.signInWithEmailAndPassword(signInUserDTO.email, signInUserDTO.password)
                    .await()?.user?.let { userAuthenticatedMapper.mapInToOut(it) }
                    ?: throw IllegalStateException("Auth user cannot be null")
                // Map the authenticated user to AuthUserDTO or throw an exception if the user is null
            } catch (ex: Exception) {
                throw SignInException("Login failed", ex) // Throw SignInException if an error occurs during sign-in
            }
        }

    @Throws(SignUpException::class)
    override suspend fun signUp(signUpUserDTO: SignUpUserDTO): AuthUserDTO =
        withContext(Dispatchers.IO) {
            try {
                firebaseAuth.createUserWithEmailAndPassword(signUpUserDTO.email, signUpUserDTO.password)
                    .await()?.user?.let { userAuthenticatedMapper.mapInToOut(it) }
                    ?: throw IllegalStateException("Auth user cannot be null")
                // Map the newly created user to AuthUserDTO or throw an exception if the user is null
            } catch (ex: Exception) {
                throw SignUpException("Sign up failed", ex) // Throw SignUpException if an error occurs during sign-up
            }
        }

    /**
     * Signs out the currently authenticated user.
     * @throws AuthException if an error occurs during the sign-out process.
     */
    @Throws(AuthException::class)
    override suspend fun closeSession() = withContext(Dispatchers.IO) {
        try {
            firebaseAuth.signOut() // Sign out the current authenticated user
        } catch (ex: Exception) {
            throw AuthException("Logout failed", ex) // Throw AuthException if an error occurs during sign-out
        }
    }
}