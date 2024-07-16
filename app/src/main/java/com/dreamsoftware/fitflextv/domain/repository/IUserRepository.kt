package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.exception.GetUserAuthenticatedUidException
import com.dreamsoftware.fitflextv.domain.exception.GetUserDetailException
import com.dreamsoftware.fitflextv.domain.exception.SignInException
import com.dreamsoftware.fitflextv.domain.exception.SignOffException
import com.dreamsoftware.fitflextv.domain.exception.SignUpException
import com.dreamsoftware.fitflextv.domain.exception.UpdateUserDetailException
import com.dreamsoftware.fitflextv.domain.model.CreateUserBO
import com.dreamsoftware.fitflextv.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import kotlin.jvm.Throws

interface IUserRepository {

    @Throws(SignInException::class)
    suspend fun signIn(email: String, password: String): UserDetailBO

    @Throws(SignUpException::class)
    suspend fun signUp(user: CreateUserBO): UserDetailBO

    @Throws(SignOffException::class)
    suspend fun signOff()

    @Throws(GetUserDetailException::class)
    suspend fun getDetail(): UserDetailBO

    @Throws(GetUserAuthenticatedUidException::class)
    suspend fun getAuthenticatedUid(): String

    @Throws(UpdateUserDetailException::class)
    suspend fun updateDetail(data: UpdatedUserRequestBO): UserDetailBO
}