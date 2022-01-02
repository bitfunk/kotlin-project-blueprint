/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.splash.component

import com.arkivanov.decompose.ComponentContext

class SplashComponent(
    componentContext: ComponentContext,
    private val onFinishedAction: () -> Unit
) : SplashContract.Component, ComponentContext by componentContext {

    override fun onFinished() {
        onFinishedAction()
    }
}
