package com.felipebertanha.presentation.intro

sealed interface IntroAction {
    data object OnSignInClick: IntroAction

    data object OnSignupClick: IntroAction

}