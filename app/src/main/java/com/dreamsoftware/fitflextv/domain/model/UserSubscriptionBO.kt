package com.dreamsoftware.fitflextv.domain.model

import java.util.Date

data class UserSubscriptionBO(
    val id: String,
    val subscriptionId: String,
    val userId: String,
    val startAt: Date,
    val validUntil: Date
)
