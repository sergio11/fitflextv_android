package com.dreamsoftware.fitflextv.data.remote.datasource.impl

import com.dreamsoftware.fitflextv.data.remote.datasource.IChallengesRemoteDataSource
import com.dreamsoftware.fitflextv.data.remote.dto.response.ChallengeDTO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class ChallengesRemoteDataSourceImpl(
    firebaseStore: FirebaseFirestore,
    challengeMapper: IOneSideMapper<Map<String, Any?>, ChallengeDTO>,
    dispatcher: CoroutineDispatcher
): TrainingProgramRemoteDataSourceImpl<ChallengeDTO>(COLLECTION_NAME, firebaseStore, challengeMapper, dispatcher), IChallengesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "challenges"
    }
}