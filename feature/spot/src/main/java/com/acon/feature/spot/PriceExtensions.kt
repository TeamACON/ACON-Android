package com.acon.feature.spot

internal fun Int.toPrice(): String {
    return "%,d원".format(this)
}