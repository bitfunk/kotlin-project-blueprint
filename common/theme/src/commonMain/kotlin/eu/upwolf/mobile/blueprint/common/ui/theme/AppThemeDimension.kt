/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppThemeDimension(
    val spacingQuarter: Dp = 2.dp,
    val spacingHalf: Dp = 4.dp,
    val spacingSingle: Dp = 8.dp,
    val spacingOneHalf: Dp = 12.dp,
    val spacingDouble: Dp = 16.dp,
    val spacingTriple: Dp = 24.dp,
    val spacingQuadruple: Dp = 32.dp,
)

internal val LocalDimension = staticCompositionLocalOf { AppThemeDimension() }
