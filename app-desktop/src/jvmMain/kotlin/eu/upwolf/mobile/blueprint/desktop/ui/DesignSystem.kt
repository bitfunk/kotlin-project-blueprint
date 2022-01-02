/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.desktop.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import eu.upwolf.mobile.blueprint.common.ui.system.AppSurface
import eu.upwolf.mobile.blueprint.common.ui.system.HeadlineLarge
import eu.upwolf.mobile.blueprint.common.ui.system.ThemeOverview
import eu.upwolf.mobile.blueprint.common.ui.system.ThemeSwitch
import eu.upwolf.mobile.blueprint.common.ui.theme.AppTheme
import eu.upwolf.mobile.blueprint.common.ui.theme.AppThemeMain

// TODO move to standalone
@Composable
fun DesignSystem() {
    val isDarkState = remember { mutableStateOf(false) }

    AppThemeMain(
        darkTheme = isDarkState.value
    ) {
        AppSurface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(AppTheme.dimension.spacingDouble),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeadlineLarge(if (isDarkState.value) "Theme is Dark" else "Theme is Light")

                Spacer(
                    modifier = Modifier.height(AppTheme.dimension.spacingDouble)
                )

                ThemeSwitch(isDarkState.value) { isDarkState.value = !isDarkState.value }

                Spacer(
                    modifier = Modifier.height(AppTheme.dimension.spacingDouble)
                )

                ThemeOverview()
            }
        }
    }
}
