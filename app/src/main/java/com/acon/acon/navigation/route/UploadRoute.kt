package com.acon.acon.navigation.route

import kotlinx.serialization.Serializable

sealed interface UploadRoute {

    @Serializable
    data object Graph : UploadRoute

    @Serializable
    data object Upload : UploadRoute

    @Serializable
    data object Success : UploadRoute
}
