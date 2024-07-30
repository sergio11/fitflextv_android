package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesByCategoryExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesByIdExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteChallengesExceptionRemote
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRemoteFeaturedChallengesExceptionRemote

interface IChallengesRemoteDataSource {

    @Throws(FetchRemoteChallengesExceptionRemote::class)
    suspend fun getChallenges(filter: TrainingFilterDTO): List<ChallengeDTO>

    @Throws(FetchRemoteChallengesByIdExceptionRemote::class)
    suspend fun getChallengeById(id: String): ChallengeDTO

    @Throws(FetchRemoteChallengesByCategoryExceptionRemote::class)
    suspend fun getChallengesByCategory(categoryId: String): List<ChallengeDTO>

    @Throws(FetchRemoteFeaturedChallengesExceptionRemote::class)
    suspend fun getFeaturedChallenges(): List<ChallengeDTO>
}