package com.acon.feature.onboarding

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object LinkedHashSetNavType : NavType<LinkedHashSet<String>>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): LinkedHashSet<String>? {
        val serialized = bundle.getString(key)
        return serialized?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): LinkedHashSet<String> {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: LinkedHashSet<String>) {
        val serialized = Json.encodeToString(value)
        bundle.putString(key, serialized)
    }
}
