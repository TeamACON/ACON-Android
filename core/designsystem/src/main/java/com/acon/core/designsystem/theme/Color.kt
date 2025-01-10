package com.acon.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)



internal val AconColors = AconColor(

    //Orange
    Orange = Color(0xFFFF5402),
    Orange900 = Color(0xFF770B00),
    Orange800 = Color(0xFF9A0C00),
    Orange700 = Color(0xFFBB1A00),
    Orange600 = Color(0xFFD83100),
    Orange500 = Color(0xFFF24B00),
    Orange400 = Color(0xFFFF6928),
    Orange300 = Color(0xFFFF8950),
    Orange200 = Color(0xFFFFAA7C),
    Orange100 = Color(0xFFFFCCAD),
    Orange50 = Color(0xFFFFEEE3),

    //Brown
    Brown = Color(0xFF723400),
    Brown900 = Color(0xFF592E0F),
    Brown800 = Color(0xFF743D14),
    Brown700 = Color(0xFF8E4E1E),
    Brown600 = Color(0xFFA7602D),
    Brown500 = Color(0xFFBE7542),
    Brown400 = Color(0xFFD28B5B),
    Brown300 = Color(0xFFE2A278),
    Brown200 = Color(0xFFF0BC9B),
    Brown100 = Color(0xFFF9D6C1),
    Brown50 = Color(0xFFFEF1EA),

    //Green
    Green = Color(0xFF3DA13F),
    Green900 = Color(0xFF004407),
    Green800 = Color(0xFF00590A),
    Green700 = Color(0xFF006F13),
    Green600 = Color(0xFF168424),
    Green500 = Color(0xFF37993A),
    Green400 = Color(0xFF57AE54),
    Green300 = Color(0xFF7AC174),
    Green200 = Color(0xFF9FD498),
    Green100 = Color(0xFFC5E6BF),
    Green50 = Color(0xFFEBF7E9),

    //Gray
    White = Color(0xFFFFFFFF),
    Gray01 = Color(0xFFEDEDED),
    Gray02 = Color(0xFFCBCBCB),
    Gray03 = Color(0xFF989898),
    Gray04 = Color(0xFF656565),
    Gray05 = Color(0xFF323232),
    Black = Color(0xFF000000),

    //Beige
    Beige = Color(0xFFFFFCEF)
)

@Immutable
data class AconColor(
    val Orange: Color,
    val Orange900: Color,
    val Orange800: Color,
    val Orange700: Color,
    val Orange600: Color,
    val Orange500: Color,
    val Orange400: Color,
    val Orange300: Color,
    val Orange200: Color,
    val Orange100: Color,
    val Orange50: Color,

    val Brown: Color,
    val Brown900: Color,
    val Brown800: Color,
    val Brown700: Color,
    val Brown600: Color,
    val Brown500: Color,
    val Brown400: Color,
    val Brown300: Color,
    val Brown200: Color,
    val Brown100: Color,
    val Brown50: Color,

    val Green: Color,
    val Green900: Color,
    val Green800: Color,
    val Green700: Color,
    val Green600: Color,
    val Green500: Color,
    val Green400: Color,
    val Green300: Color,
    val Green200: Color,
    val Green100: Color,
    val Green50: Color,

    val White: Color,
    val Gray01: Color,
    val Gray02: Color,
    val Gray03: Color,
    val Gray04: Color,
    val Gray05: Color,
    val Black: Color,

    val Beige: Color,
)

internal val LocalAconColor = staticCompositionLocalOf {
    AconColors
}