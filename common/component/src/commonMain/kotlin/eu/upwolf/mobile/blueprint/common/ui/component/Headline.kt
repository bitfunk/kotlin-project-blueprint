/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import eu.upwolf.mobile.blueprint.common.ui.theme.AppTheme

@Composable
fun HeadlineSmall(
    text: String,
) {
    Text(
        text = text,
        color = AppTheme.colorScheme.onBackground,
        style = AppTheme.typography.headlineSmall
    )
}

@Composable
fun HeadlineMedium(
    text: String,
) {
    Text(
        text = text,
        color = AppTheme.colorScheme.onBackground,
        style = AppTheme.typography.headlineMedium
    )
}

@Composable
fun HeadlineLarge(
    text: String,
) {
    Text(
        text = text,
        color = AppTheme.colorScheme.onBackground,
        style = AppTheme.typography.headlineLarge
    )
}
