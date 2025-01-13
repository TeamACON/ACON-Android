package com.acon.data.error

abstract class RootError: Throwable() {
    open val code: Int = 0
}