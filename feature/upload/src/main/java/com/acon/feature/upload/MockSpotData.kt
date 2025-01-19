package com.acon.feature.upload

object MockSpotData {
    private val mockSpots = listOf(
        LocationItem(
            spotId = 1,
            name = "스태픽스",
            address = "서울 종로구 사직로9길 22 102호",
            spotType = SpotType.CAFE
        ),
        LocationItem(
            spotId = 2,
            name = "빽다방",
            address = "서울 종로구 새문안로 3길 23",
            spotType = SpotType.CAFE
        ),
        LocationItem(
            spotId = 3,
            name = "봉된장",
            address = "서울 종로구 사직로 8길 34",
            spotType = SpotType.RESTAURANT
        ),
        LocationItem(
            spotId = 4,
            name = "스스스태픽스",
            address = "서울 종로구 사직로9길 22 102호",
            spotType = SpotType.CAFE
        ),
    )

    fun searchSpots(query: String): List<LocationItem> {
        return if (query.isBlank()) {
            emptyList()
        } else {
            mockSpots.filter { 
                it.name.contains(query, ignoreCase = true) || 
                it.address.contains(query, ignoreCase = true)
            }
        }
    }
}
