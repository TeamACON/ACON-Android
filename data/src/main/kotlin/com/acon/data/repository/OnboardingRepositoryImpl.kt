package com.acon.data.repository

import com.acon.data.datasource.local.OnboardingLocalDataSource
import com.acon.data.datasource.remote.OnboardingRemoteDataSource
import com.acon.data.dto.request.PostOnboardingResultRequest
import com.acon.data.error.runCatchingWith
import com.acon.domain.error.onboarding.PostOnboardingResultError
import com.acon.domain.model.onboarding.OnboardingPreferences
import com.acon.domain.repository.OnboardingRepository
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
            onboardingRemoteDataSource.postResult(
                PostOnboardingResultRequest(
                    dislikeFoodList = dislikeFoodList,
                    favoriteCuisineRank = favoriteCuisineRank,
                    favoriteSpotType = favoriteSpotType,
                    favoriteSpotStyle = favoriteSpotStyle,
                    favoriteSpotRank = favoriteSpotRank
                )
            )
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
        onboardingLocalDataSource.favoriteSpotRank
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

//var dislikeFoodList: Set<String>
//var favoriteCuisineRank: List<String>
//var favoriteSpotType: String
//var favoriteSpotStyle: String
//var favoriteSpotRank: List<String>