/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        ThemeColorSchemeDark
    } else {
        ThemeColorSchemeLight
    }

    Theme(
        colorScheme = colorScheme,
        content = content,
    )
}

object Theme {
    val colorScheme: ThemeColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: ThemeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val dimension: ThemeDimension
        @Composable
        @ReadOnlyComposable
        get() = LocalDimension.current
}

@Composable
expect fun PlatformThemeSetup()

@Composable
fun Theme(
    colorScheme: ThemeColorScheme = Theme.colorScheme,
    typography: ThemeTypography = Theme.typography,
    dimension: ThemeDimension = Theme.dimension,
    content: @Composable () -> Unit
) {
    PlatformThemeSetup()

    ProvideTheme(
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
fun ProvideTheme(
    colorScheme: ThemeColorScheme,
    dimension: ThemeDimension,
    typography: ThemeTypography,
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
