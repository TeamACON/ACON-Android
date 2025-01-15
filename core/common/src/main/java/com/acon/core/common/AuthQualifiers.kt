package com.acon.core.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Auth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoAuth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Refresh