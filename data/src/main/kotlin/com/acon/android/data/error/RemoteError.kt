package com.acon.android.data.error

import java.io.IOException

data class RemoteError(
    val statusCode: Int,
    val errorCode: Int,
    override val message: String,
    val httpErrorMessage: String
) : IOException()