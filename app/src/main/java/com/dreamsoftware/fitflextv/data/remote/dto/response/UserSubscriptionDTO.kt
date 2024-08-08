package com.dreamsoftware.fitflextv.data.remote.dto.response

import com.google.firebase.Timestamp

data class UserSubscriptionDTO(
    val id: String,
    val subscriptionId: String,
    val userId: String,
    val startAt: Timestamp,
    val validUntil: Timestamp
)
