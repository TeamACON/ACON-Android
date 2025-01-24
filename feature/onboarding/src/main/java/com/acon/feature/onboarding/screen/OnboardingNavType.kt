package com.acon.feature.onboarding.screen

import android.os.Bundle
import androidx.navigation.NavType
import com.acon.feature.onboarding.OnboardingRoute
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

object OnboardingNavType : NavType<OnboardingRoute.LastLoading>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): OnboardingRoute.LastLoading? {
        val serialized = bundle.getString(key)
        return serialized?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): OnboardingRoute.LastLoading {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: OnboardingRoute.LastLoading) {
        val serialized = Json.encodeToString(value)
        bundle.putString(key, serialized)
    }
}
