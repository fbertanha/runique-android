package com.felipebertanha.auth.domain

data class PasswordValidationState(
    val hasMinLength: Boolean = false,
    val hasNumber: Boolean = false,
    val hasLowerCaseChar: Boolean = false,
    val hasUpperCaseChar: Boolean = false,
) {
    val isValid: Boolean
        get() = hasMinLength
                && hasNumber
                && hasLowerCaseChar
                && hasUpperCaseChar
}
