package com.dreamsoftware.fitflextv.data.repository.challenges

import com.dreamsoftware.fitflextv.data.entities.Challenge

interface ChallengesRepository {
    fun getChallenges(): List<Challenge>
    fun getChallengeById(id: String): Challenge
}