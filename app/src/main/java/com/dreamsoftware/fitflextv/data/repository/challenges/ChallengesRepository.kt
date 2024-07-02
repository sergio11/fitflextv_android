package com.dreamsoftware.fitflextv.data.repository.challenges

import com.dreamsoftware.fitflextv.domain.model.ChallengeBO

interface ChallengesRepository {
    fun getChallenges(): List<ChallengeBO>
    fun getChallengeById(id: String): ChallengeBO
}