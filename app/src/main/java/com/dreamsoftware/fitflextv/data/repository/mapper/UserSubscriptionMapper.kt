package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.UserSubscriptionDTO
import com.dreamsoftware.fitflextv.domain.model.UserSubscriptionBO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper

internal class UserSubscriptionMapper : IOneSideMapper<UserSubscriptionDTO, UserSubscriptionBO> {

    override fun mapInToOut(input: UserSubscriptionDTO): UserSubscriptionBO = with(input) {
        UserSubscriptionBO(
            id = id,
            subscriptionId = subscriptionId,
            userId = userId,
            startAt = startAt.toDate(),
            validUntil = validUntil.toDate()
        )
    }

    override fun mapInListToOutList(input: Iterable<UserSubscriptionDTO>): Iterable<UserSubscriptionBO> =
        input.map(::mapInToOut)
}