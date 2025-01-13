package com.acon.domain.error

abstract class RootError: Throwable() {
    open val code: Int = 0
}