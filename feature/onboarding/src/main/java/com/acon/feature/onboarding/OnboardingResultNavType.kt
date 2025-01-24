package com.acon.feature.onboarding

import android.net.Uri
import android.os.Bundle
import androidx.core.os.BundleCompat
import androidx.navigation.NavType
import com.acon.feature.onboarding.screen.OnboardingScreen.OnboardingResult
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object OnboardingResultNavType : NavType<OnboardingResult>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): OnboardingResult? {
        return BundleCompat.getParcelable(bundle, key, OnboardingResult::class.java)
    }

    override fun parseValue(value: String): OnboardingResult {
        return Json.decodeFromString<OnboardingResult>(value)
    }

    override fun put(bundle: Bundle, key: String, value: OnboardingResult) {
        return bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: OnboardingResult): String {
        return Uri.encode(Json.encodeToString(value))
    }
}