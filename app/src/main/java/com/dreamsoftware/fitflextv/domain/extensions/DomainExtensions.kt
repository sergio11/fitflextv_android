package com.dreamsoftware.fitflextv.domain.extensions

private const val SECURE_PIN_LENGTH = 6
private const val MIN_USERNAME_LENGTH = 5
private const val MIN_ALIAS_LENGTH = 5
const val MIN_PASSWORD_LENGTH = 8

fun String.isEmailValid() =
    matches(Regex("^[a-zA-Z0-9_!#\$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\$"))

fun String.isEmailNotValid() = !isEmailValid()

fun Int.isSecurePinValid() = toString().length == SECURE_PIN_LENGTH

fun Int.isSecurePinNotValid() = !isSecurePinValid()

fun String.isProfileAliasValid() = length >= MIN_ALIAS_LENGTH

fun String.isProfileAliasNotValid() = !isProfileAliasValid()

fun String.isPasswordValid(): Boolean {
    return length >= MIN_PASSWORD_LENGTH &&
            any { it.isLowerCase() } &&
            any { it.isUpperCase() } &&
            any { it.isDigit() } &&
            any { it in setOf('!', '@', '#', '$', '%', '^', '&', '*') }
}

fun String.isPasswordNotValid(): Boolean = !isPasswordValid()

fun String.isUsernameValid(): Boolean =
    length >= MIN_USERNAME_LENGTH

fun String.isUsernameNotValid() = !isUsernameValid()

fun String.isFirstNameValid(): Boolean = isNotBlank()

fun String.isFirstNameNotValid() = !isFirstNameValid()

fun String.isLastNameValid(): Boolean = isNotBlank()

fun String.isLastNameNotValid() = !isLastNameValid()