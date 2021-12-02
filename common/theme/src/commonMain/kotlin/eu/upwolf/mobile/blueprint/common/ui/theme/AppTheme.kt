/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

@Composable
fun AppThemeMain(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        AppThemeColorSchemeDark
    } else {
        AppThemeColorSchemeLight
    }

    AppTheme(
        colorScheme = colorScheme,
        content = content,
    )
}

object AppTheme {
    val colorScheme: AppThemeColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: AppThemeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val dimension: AppThemeDimension
        @Composable
        @ReadOnlyComposable
        get() = LocalDimension.current
}

@Composable
expect fun AppThemePlatformSetup()

@Composable
fun AppTheme(
    colorScheme: AppThemeColorScheme = AppTheme.colorScheme,
    typography: AppThemeTypography = AppTheme.typography,
    dimension: AppThemeDimension = AppTheme.dimension,
    content: @Composable () -> Unit
) {
    AppThemePlatformSetup()

    ProvideAppTheme(
        colorScheme,
        dimension,
        typography,
    ) {
        MaterialTheme(
            colorScheme = DebugColorScheme,
            // shapes = Shapes,
            content = content
        )
    }
}

@Composable
fun ProvideAppTheme(
    colorScheme: AppThemeColorScheme,
    dimension: AppThemeDimension,
    typography: AppThemeTypography,
    content: @Composable () -> Unit
) {
    val rememberedColorScheme = remember {
        colorScheme.copy()
    }.apply {
        update(colorScheme)
    }

    CompositionLocalProvider(
        LocalColorScheme provides rememberedColorScheme,
        LocalDimension provides dimension,
        LocalTypography provides typography,
        content = content
    )
}
