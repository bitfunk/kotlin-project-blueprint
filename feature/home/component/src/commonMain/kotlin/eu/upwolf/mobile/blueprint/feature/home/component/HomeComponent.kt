/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.home.component

import com.arkivanov.decompose.ComponentContext

class HomeComponent(
    componentContext: ComponentContext,
    private val onFinishedAction: () -> Unit
) : HomeContract.Component, ComponentContext by componentContext {

    override fun onFinished() {
        onFinishedAction()
    }
}
