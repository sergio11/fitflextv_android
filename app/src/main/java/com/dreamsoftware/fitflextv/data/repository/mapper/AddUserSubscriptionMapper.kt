package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.request.AddUserSubscriptionDTO
import com.dreamsoftware.fitflextv.domain.model.AddUserSubscriptionBO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper

internal class AddUserSubscriptionMapper : IOneSideMapper<AddUserSubscriptionBO, AddUserSubscriptionDTO> {

    override fun mapInToOut(input: AddUserSubscriptionBO): AddUserSubscriptionDTO = with(input) {
        AddUserSubscriptionDTO(
            id = id,
            subscriptionId = subscriptionId,
            userId = userId,
            validUntil = validUntil
        )
    }

    override fun mapInListToOutList(input: Iterable<AddUserSubscriptionBO>): Iterable<AddUserSubscriptionDTO> =
        input.map(::mapInToOut)
}