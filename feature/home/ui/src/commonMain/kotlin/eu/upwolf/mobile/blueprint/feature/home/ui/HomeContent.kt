/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.home.ui

import androidx.compose.runtime.Composable
import eu.upwolf.mobile.blueprint.feature.home.component.HomeContract

@Composable
fun HomeContent(
    component: HomeContract.Component
) {
    HomePage {
        component.onFinished()
    }
}
