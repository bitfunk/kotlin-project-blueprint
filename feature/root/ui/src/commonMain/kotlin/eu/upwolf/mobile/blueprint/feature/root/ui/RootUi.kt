/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.root.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import eu.upwolf.mobile.blueprint.feature.home.ui.HomeContent
import eu.upwolf.mobile.blueprint.feature.root.component.RootComponentContract
import eu.upwolf.mobile.blueprint.feature.root.component.RootComponentContract.Child
import eu.upwolf.mobile.blueprint.feature.splash.ui.SplashContent

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootContent(component: RootComponentContract.Component) {
    Children(
        routerState = component.routerState,
        animation = crossfadeScale()
    ) {
        when (val child = it.instance) {
            is Child.Splash -> SplashContent(component = child.component)
            is Child.Home -> HomeContent(component = child.component)
        }
    }
}
