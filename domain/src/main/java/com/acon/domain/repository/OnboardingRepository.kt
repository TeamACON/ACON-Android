package com.acon.domain.repository

import com.acon.domain.model.onboarding.OnboardingPreferences
import kotlinx.coroutines.flow.StateFlow

interface OnboardingRepository {

    suspend fun postOnboardingResult(
        dislikeFoodList: Set<String>,
        favoriteCuisineRank: List<String>,
        favoriteSpotType: String,
        favoriteSpotStyle: String,
        favoriteSpotRank: List<String>
    ): Result<Unit>

    fun postDislikeFood(choice: Set<String>)
    fun postFavoriteCuisineRank(choice: List<String>)
    fun postFavoriteSpotType(choice: String)
    fun postFavoriteSpotStyle(choice: String)
    fun postFavoriteSpotRank(choice: List<String>)

    fun getOnboardingResults(): OnboardingPreferences

    val onboardingResultStateFlow: StateFlow<Result<Unit>?>
}
