package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper

internal class SubscriptionRemoteMapper: IOneSideMapper<Map<String, Any?>, SubscriptionDTO> {

    private companion object {
        const val UID_KEY = "uid"
        const val PERIOD_TIME_KEY = "periodTime"
        const val PRICE_KEY = "price"
    }

    override fun mapInToOut(input: Map<String, Any?>): SubscriptionDTO = with(input) {
        SubscriptionDTO(
            id = get(UID_KEY) as String,
            periodTime = get(PERIOD_TIME_KEY) as String,
            price = get(PRICE_KEY) as String
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<SubscriptionDTO> =
        input.map(::mapInToOut)
}