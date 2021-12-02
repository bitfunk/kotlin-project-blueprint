/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppThemeSize(
    val smaller: Dp = 8.dp,
    val small: Dp = 16.dp,
    val medium: Dp = 32.dp,
    val large: Dp = 64.dp,
    val larger: Dp = 128.dp,
)

internal val LocalSize = staticCompositionLocalOf { AppThemeSize() }
