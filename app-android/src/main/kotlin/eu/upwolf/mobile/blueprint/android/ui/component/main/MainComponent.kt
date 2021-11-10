/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.android.ui.component.main

import com.arkivanov.decompose.ComponentContext

class MainComponent(
    componentContext: ComponentContext,
    private val onBottomBarItem: () -> Unit
) : MainContract, ComponentContext by componentContext {

    override fun onBottomBarItemClicked() {
        onBottomBarItem()
    }
}
