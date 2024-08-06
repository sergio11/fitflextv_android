package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.AddUserSubscriptionDTO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import java.util.Date

internal class AddUserSubscriptionRemoteMapper:
    IOneSideMapper<AddUserSubscriptionDTO, Map<String, Any?>> {

    private companion object {
        const val UID_KEY = "uid"
        const val SUBSCRIPTION_KEY = "subscriptionId"
        const val USER_KEY = "userId"
        const val START_AT_KEY = "startAt"
        const val VALID_UNTIL_KEY = "validUntil"
    }

    override fun mapInToOut(input: AddUserSubscriptionDTO): Map<String, Any?> = with(input) {
        hashMapOf(
            UID_KEY to id,
            SUBSCRIPTION_KEY to subscriptionId,
            USER_KEY to userId,
            START_AT_KEY to Date(), // Generate current Date for startAt
            VALID_UNTIL_KEY to Date(validUntil) // Convert validUntil Long to Date
        )
    }

    override fun mapInListToOutList(input: Iterable<AddUserSubscriptionDTO>): Iterable<Map<String, Any?>> =
        input.map(::mapInToOut)
}