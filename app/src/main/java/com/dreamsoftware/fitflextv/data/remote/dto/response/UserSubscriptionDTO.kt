package com.dreamsoftware.fitflextv.data.remote.dto.response

data class UserSubscriptionDTO(
    val id: String,
    val subscriptionId: String,
    val userId: String,
    val startAt: Long,
    val validUntil: Long
)
