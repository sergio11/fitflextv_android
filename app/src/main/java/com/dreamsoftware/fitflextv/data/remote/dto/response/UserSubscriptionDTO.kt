package com.dreamsoftware.fitflextv.data.remote.dto.response

import java.util.Date

data class UserSubscriptionDTO(
    val id: String,
    val subscriptionId: String,
    val userId: String,
    val startAt: Date,
    val validUntil: Date
)
