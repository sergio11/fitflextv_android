package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper

internal class UserRemoteMapper: IOneSideMapper<Map<String, Any?>, UserResponseDTO> {

    private companion object {

    }

    override fun mapInToOut(input: Map<String, Any?>): UserResponseDTO {
        TODO("Not yet implemented")
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<UserResponseDTO> {
        TODO("Not yet implemented")
    }
}