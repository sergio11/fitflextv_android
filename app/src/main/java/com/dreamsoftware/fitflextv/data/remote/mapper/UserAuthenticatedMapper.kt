package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.AuthUserDTO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.google.firebase.auth.FirebaseUser

internal class UserAuthenticatedMapper : IOneSideMapper<FirebaseUser, AuthUserDTO> {

    override fun mapInToOut(input: FirebaseUser): AuthUserDTO = with(input) {
        AuthUserDTO(
            uid = uid,
            displayName = displayName,
            email = email
        )
    }

    override fun mapInListToOutList(input: Iterable<FirebaseUser>): Iterable<AuthUserDTO> =
        input.map(::mapInToOut)
}