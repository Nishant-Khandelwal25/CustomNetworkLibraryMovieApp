package com.example.customnetworkapimovie.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)

val shimmerLightGray = Color(0xFFF1F1F1)
val shimmerMediumGray = Color(0xFFE3E3E3)
val shimmerDarkGray = Color(0xFF1D1D1D)

val topAppBarContentColor
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else Color.White

val topAppBarBackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Purple80