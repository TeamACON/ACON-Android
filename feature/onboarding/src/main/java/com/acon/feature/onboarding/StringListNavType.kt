package com.acon.feature.onboarding

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object StringListNavType : NavType<List<String>>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): List<String>? {
        return bundle.getStringArrayList(key)
    }

    override fun parseValue(value: String): List<String> {
        return value.split(",")
    }

    override fun put(bundle: Bundle, key: String, value: List<String>) {
        bundle.putStringArrayList(key, ArrayList(value))
    }
}
