package com.correa.unknownword.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.correa.unknownword.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
val FontRoboto = FontFamily(
    Font(R.font.roboto_thin, FontWeight.W100),
    Font(R.font.roboto_light, FontWeight.W300),
    Font(R.font.roboto_regular, FontWeight.W400),
    Font(R.font.roboto_medium, FontWeight.W500),
    Font(R.font.roboto_bold, FontWeight.W700),
    Font(R.font.roboto_black, FontWeight.W900),
)

val TypographyRoboto = Typography(
    displayLarge = TextStyle(
        fontFamily = FontRoboto,
        fontWeight = FontWeight.W900,
        fontSize = 64.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontRoboto,
        fontWeight = FontWeight.W900,
        fontSize = 22.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontRoboto,
        fontWeight = FontWeight.W900,
        fontSize = 26.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontRoboto,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontRoboto,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontRoboto,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontRoboto,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp
    ),
)