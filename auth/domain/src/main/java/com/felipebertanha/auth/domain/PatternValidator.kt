package com.felipebertanha.auth.domain

interface PatternValidator {

    fun matches(value: String): Boolean
}