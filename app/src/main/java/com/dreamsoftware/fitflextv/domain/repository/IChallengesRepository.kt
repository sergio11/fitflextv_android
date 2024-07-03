package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.model.ChallengeBO

interface IChallengesRepository {
    fun getChallenges(): List<ChallengeBO>
    fun getChallengeById(id: String): ChallengeBO
}