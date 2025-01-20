package com.acon.domain.type

sealed interface OptionType {

    fun getName(): String {
        return this::class.java.simpleName
    }

    enum class RestaurantFeatureOptionType : OptionType {
        KOREAN, WESTERN, CHINESE, JAPANESE, SNACK, ASIAN, BAR, EXCLUDE_FRANCHISE
    }

    enum class CafeFeatureOptionType : OptionType {
        LARGE, GOOD_VIEW, DESSERT, TERRACE, EXCLUDE_FRANCHISE
    }

    enum class CompanionTypeOptionType : OptionType {
        FAMILY, DATE, FRIEND, ALONE, GROUP
    }

    enum class VisitPurposeOptionType : OptionType {
        MEETING, STUDY
    }
}