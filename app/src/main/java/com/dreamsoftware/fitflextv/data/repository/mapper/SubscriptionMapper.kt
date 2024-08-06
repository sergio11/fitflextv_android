package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper

internal class SubscriptionMapper : IOneSideMapper<SubscriptionDTO, SubscriptionBO> {

    override fun mapInToOut(input: SubscriptionDTO): SubscriptionBO = with(input) {
        SubscriptionBO(
            id = id,
            periodTime = periodTime,
            price = price
        )
    }

    override fun mapInListToOutList(input: Iterable<SubscriptionDTO>): Iterable<SubscriptionBO> =
        input.map(::mapInToOut)
}