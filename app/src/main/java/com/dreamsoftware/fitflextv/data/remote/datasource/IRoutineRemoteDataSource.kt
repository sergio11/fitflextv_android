package com.dreamsoftware.fitflextv.data.remote.datasource

import com.dreamsoftware.fitflextv.data.remote.dto.request.TrainingFilterDTO
import com.dreamsoftware.fitflextv.data.remote.dto.response.RoutineDTO
import com.dreamsoftware.fitflextv.data.remote.exception.FetchFeaturedRoutinesRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRoutineByCategoryRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRoutineByIdRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRoutinesRemoteException
import com.dreamsoftware.fitflextv.data.remote.exception.FetchRoutinesRecommendedRemoteException

interface IRoutineRemoteDataSource {

    @Throws(FetchRoutinesRemoteException::class)
    suspend fun getRoutines(filter: TrainingFilterDTO): List<RoutineDTO>

    @Throws(FetchRoutineByIdRemoteException::class)
    suspend fun getRoutineById(id: String): RoutineDTO

    @Throws(FetchRoutineByCategoryRemoteException::class)
    suspend fun getRoutineByCategory(categoryId: String): List<RoutineDTO>

    @Throws(FetchFeaturedRoutinesRemoteException::class)
    suspend fun getFeaturedRoutines(): List<RoutineDTO>

    @Throws(FetchRoutinesRecommendedRemoteException::class)
    suspend fun getRecommendedRoutines(): List<RoutineDTO>
}