/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.splash.ui

import androidx.compose.runtime.Composable
import eu.upwolf.mobile.blueprint.feature.splash.component.SplashContract

@Composable
fun SplashContent(
    component: SplashContract.Component
) {
    SplashPage {
        component.onFinished()
    }
}
