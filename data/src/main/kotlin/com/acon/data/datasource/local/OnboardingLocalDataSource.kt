package com.acon.data.datasource.local

interface OnboardingLocalDataSource {
    var dislikeFoodList: Set<String>
    var favoriteCuisineRank: List<String>
    var favoriteSpotType: String
    var favoriteSpotStyle: String
    var favoriteSpotRank: List<String>

    fun clear()
}