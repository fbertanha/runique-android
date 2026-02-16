package com.felipebertanha.auth.data

import android.util.Patterns
import com.felipebertanha.auth.domain.PatternValidator

object EmailValidator : PatternValidator {
    override fun matches(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}