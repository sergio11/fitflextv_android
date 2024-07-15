package com.dreamsoftware.fitflextv.data.remote.dto.request

data class UpdatedUserRequestDTO(
    //Updated first name of the user.
    val firstName: String? = null,
    // Updated last name of the user.
    val lastName: String? = null,
    // Updated username of the user.
    val username: String? = null
)
