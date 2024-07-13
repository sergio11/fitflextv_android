package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.ChallengeDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesByCategoryException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesByIdException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesException

interface IChallengesRemoteDataSource {

    @Throws(FetchRemoteChallengesException::class)
    suspend fun getChallenges(): List<ChallengeDTO>

    @Throws(FetchRemoteChallengesByIdException::class)
    suspend fun getChallengeById(id: String): ChallengeDTO

    @Throws(FetchRemoteChallengesByCategoryException::class)
    suspend fun getChallengesByCategory(categoryId: String): List<ChallengeDTO>
}