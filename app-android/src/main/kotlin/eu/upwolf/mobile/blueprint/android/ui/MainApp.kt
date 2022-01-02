/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.android.ui

import androidx.compose.runtime.Composable
import com.google.accompanist.insets.ProvideWindowInsets
import eu.upwolf.mobile.blueprint.common.ui.theme.AppThemeMain

@Composable
fun MainApp(
    content: @Composable () -> Unit
) {
    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        AppThemeMain {
            content()
        }
    }
}
