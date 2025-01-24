package com.acon.feature.onboarding

import android.net.Uri
import android.os.Bundle
import androidx.core.os.BundleCompat
import androidx.navigation.NavType
import com.acon.feature.onboarding.screen.OnboardingScreen.OnboardingResult
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object StringSetNavType : NavType<Set<String>>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Set<String>? {
        val stringList = bundle.getStringArrayList(key)
        return stringList?.toSet()
    }

    override fun parseValue(value: String): Set<String> {
        return value.split(",").toSet()
    }

    override fun put(bundle: Bundle, key: String, value: Set<String>) {
        bundle.putStringArrayList(key, ArrayList(value))
    }
}