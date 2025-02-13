package com.acon.android.core.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Auth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoAuth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Naver

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TokenInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ResponseInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NaverAuthInterceptor

