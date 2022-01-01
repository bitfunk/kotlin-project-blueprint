/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.ui.system

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import eu.upwolf.mobile.blueprint.common.ui.theme.AppTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppTheme.colorScheme.primary,
            contentColor = AppTheme.colorScheme.onPrimary,
        ),
        contentPadding = PaddingValues(
            start = AppTheme.dimension.spacingDouble,
            top = AppTheme.dimension.spacingSingle,
            end = AppTheme.dimension.spacingDouble,
            bottom = AppTheme.dimension.spacingSingle,
        )
    ) {
        Text(
            text = text,
            style = AppTheme.typography.labelMedium
        )
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppTheme.colorScheme.secondary,
            contentColor = AppTheme.colorScheme.onSecondary,
        ),
        contentPadding = PaddingValues(
            start = AppTheme.dimension.spacingDouble,
            top = AppTheme.dimension.spacingSingle,
            end = AppTheme.dimension.spacingDouble,
            bottom = AppTheme.dimension.spacingSingle,
        )
    ) {
        Text(
            text = text,
            style = AppTheme.typography.labelMedium
        )
    }
}
