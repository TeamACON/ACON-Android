package com.acon.acon.navigation.route

import kotlinx.serialization.Serializable

@Serializable
sealed interface SignInRoute {

    @Serializable
    data object SignIn : SignInRoute
}