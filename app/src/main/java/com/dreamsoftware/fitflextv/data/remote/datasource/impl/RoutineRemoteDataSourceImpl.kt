package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IRoutineRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.response.RoutineDTO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class RoutineRemoteDataSourceImpl(
    firebaseStore: FirebaseFirestore,
    routineMapper: IOneSideMapper<Map<String, Any?>, RoutineDTO>,
    dispatcher: CoroutineDispatcher
) : TrainingProgramRemoteDataSourceImpl<RoutineDTO>(COLLECTION_NAME, firebaseStore, routineMapper, dispatcher),
    IRoutineRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "routines"
    }
}