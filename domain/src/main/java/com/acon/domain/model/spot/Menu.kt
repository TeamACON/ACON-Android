package com.acon.domain.model.spot

import androidx.compose.runtime.Immutable

@Immutable
data class Menu(
    val id: String,
    val name: String,
    val price: Int,
    val image: String,
)