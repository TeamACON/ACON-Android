package com.acon.android.feature.signin.screen

import kotlinx.serialization.Serializable

@Serializable
sealed interface SignInRoute {

    @Serializable
    data object Graph : SignInRoute

    @Serializable
    data object SignIn : SignInRoute
}