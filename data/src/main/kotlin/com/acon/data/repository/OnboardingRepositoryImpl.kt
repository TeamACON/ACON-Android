package com.acon.data.repository

import android.util.Log
import com.acon.data.datasource.local.OnboardingLocalDataSource
import com.acon.data.datasource.remote.OnboardingRemoteDataSource
import com.acon.data.dto.request.PostOnboardingResultRequest
import com.acon.data.error.runCatchingWith
import com.acon.domain.error.onboarding.PostOnboardingResultError
import com.acon.domain.model.onboarding.OnboardingPreferences
import com.acon.domain.repository.OnboardingRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingRemoteDataSource: OnboardingRemoteDataSource,
    private val onboardingLocalDataSource: OnboardingLocalDataSource
) : OnboardingRepository {

    override suspend fun postOnboardingResult(
        dislikeFoodList: Set<String>,
        favoriteCuisineRank: List<String>,
        favoriteSpotType: String,
        favoriteSpotStyle: String,
        favoriteSpotRank: List<String>
    ): Result<Unit> {
        return runCatchingWith(*PostOnboardingResultError.createErrorInstances()){
            val request = PostOnboardingResultRequest(
                dislikeFoodList = dislikeFoodList,
                favoriteCuisineRank = favoriteCuisineRank,
                favoriteSpotType = favoriteSpotType,
                favoriteSpotStyle = favoriteSpotStyle,
                favoriteSpotRank = favoriteSpotRank
            )

            // 요청 데이터 로그 출력
            val requestJson = Json.encodeToString(request)
            Log.d("OnboardingRequest", "Request Data: $requestJson")

            val response = onboardingRemoteDataSource.postResult(request)

            if (response.isSuccessful) {
                Log.d("OnboardingResponse", "Success: ${response.code()} - ${response.body()}")
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Log.e("OnboardingResponse", "Error: ${response.code()} - $errorBody")
                throw RuntimeException("Server error: $errorBody")
            }
        }
    }

    override fun postDislikeFood(choice: Set<String>){
        onboardingLocalDataSource.dislikeFoodList = choice
    }

    override fun postFavoriteCuisineRank(choice: List<String>) {
        onboardingLocalDataSource.favoriteCuisineRank = choice
    }

    override fun postFavoriteSpotType(choice: String) {
        onboardingLocalDataSource.favoriteSpotType = choice
    }

    override fun postFavoriteSpotStyle(choice: String) {
        onboardingLocalDataSource.favoriteSpotStyle = choice
    }

    override fun postFavoriteSpotRank(choice: List<String>) {
        onboardingLocalDataSource.favoriteSpotRank = choice
    }

    override fun getOnboardingResults(): OnboardingPreferences {
        return OnboardingPreferences(
            dislikeFoodList = onboardingLocalDataSource.dislikeFoodList,
            favoriteCuisineRank = onboardingLocalDataSource.favoriteCuisineRank,
            favoriteSpotType = onboardingLocalDataSource.favoriteSpotType,
            favoriteSpotStyle = onboardingLocalDataSource.favoriteSpotStyle,
            favoriteSpotRank = onboardingLocalDataSource.favoriteSpotRank
        )
    }
}