package com.acon.feature.upload

data class UploadState(
    val selectedCount: Int = 0,
    val maxCount: Int = 5,
    val ownedDotoriCount: Int = 25, //서버에서 받아 오는 도토리 수
    val isButtonEnabled: Boolean = false
)
