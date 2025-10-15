package com.felipebertanha.core.presentation.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UiText {

    data class Dynamic(val value: String) : UiText

    class StringResource(
        @StringRes val id: Int, val args: Array<Any> = arrayOf()
    ) : UiText

    @Composable
    fun asString(): String {
        return when (this) {
            is Dynamic -> value
            is StringResource -> stringResource(id = id, formatArgs = args)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is Dynamic -> value
            is StringResource -> context.getString(id, args)
        }
    }
}