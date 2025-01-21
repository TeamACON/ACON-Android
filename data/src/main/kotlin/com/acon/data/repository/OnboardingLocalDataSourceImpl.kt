package com.acon.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.acon.data.datasource.local.OnboardingLocalDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class OnboardingLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
): OnboardingLocalDataSource{

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override var dislikeFoodList: Set<String>
        get() = sharedPreferences.getStringSet("DISLIKE_FOOD_LIST", emptySet()) ?: emptySet()
        set(value) = sharedPreferences.edit { putStringSet("DISLIKE_FOOD_LIST", value) }

    override var favoriteCuisineRank: List<String>
        get() = sharedPreferences.getString("FAVORITE_CUISINE_RANK", "")?.split(",") ?: emptyList()
        set(value) = sharedPreferences.edit { putString("FAVORITE_CUISINE_RANK", value.joinToString(",")) }

    override var favoriteSpotType: String
        get() = sharedPreferences.getString("FAVORITE_SPOT_TYPE", INITIAL_VALUE).toString()
        set(value) = sharedPreferences.edit { putString("FAVORITE_SPOT_TYPE", value) }

    override var favoriteSpotStyle: String
        get() = sharedPreferences.getString("FAVORITE_SPOT_STYLE", INITIAL_VALUE).toString()
        set(value) = sharedPreferences.edit { putString("FAVORITE_SPOT_STYLE", value) }

    override var favoriteSpotRank: List<String>
        get() = sharedPreferences.getString("FAVORITE_SPOT_RANK", "")?.split(",") ?: emptyList()
        set(value) = sharedPreferences.edit { putString("FAVORITE_SPOT_RANK", value.joinToString(",")) }

    override fun clear() = sharedPreferences.edit { clear() }

    companion object {
        const val PREFERENCES_NAME = "onboarding_result"
        const val DISLIKE_FOOD_LIST = ""
        const val FAVORITE_CUISINE_RANK = ""
        const val FAVORITE_SPOT_TYPE = ""
        const val FAVORITE_SPOT_STYLE = ""
        const val FAVORITE_SPOT_RANK = ""
        const val INITIAL_VALUE = ""
    }
}