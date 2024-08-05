package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchChallengesByCategoryRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchChallengesByIdRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchChallengesRecommendedRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchChallengesRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchFeaturedChallengesRemoteException

interface IChallengesRemoteDataSource {

    @Throws(FetchChallengesRemoteException::class)
    suspend fun getChallenges(filter: TrainingFilterDTO): List<ChallengeDTO>

    @Throws(FetchChallengesByIdRemoteException::class)
    suspend fun getChallengeById(id: String): ChallengeDTO

    @Throws(FetchChallengesByCategoryRemoteException::class)
    suspend fun getChallengesByCategory(categoryId: String): List<ChallengeDTO>

    @Throws(FetchFeaturedChallengesRemoteException::class)
    suspend fun getFeaturedChallenges(): List<ChallengeDTO>

    @Throws(FetchChallengesRecommendedRemoteException::class)
    suspend fun getRecommendedChallenges(): List<ChallengeDTO>
}