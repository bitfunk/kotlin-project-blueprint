/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.android.ui.component.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import eu.upwolf.mobile.blueprint.android.ui.component.main.MainComponent
import eu.upwolf.mobile.blueprint.android.ui.component.main.MainContract

class RootComponent(
    componentContext: ComponentContext
) : RootContract, ComponentContext by componentContext {

    private val router = router<Config, RootContract.Child>(
        initialConfiguration = Config.Main,
        handleBackButton = true,
        childFactory = ::child
    )

    override val routerState: Value<RouterState<*, RootContract.Child>>
        get() = TODO("Not yet implemented")

    private fun child(config: Config, componentContext: ComponentContext): RootContract.Child =
        when (config) {
            is Config.Main -> RootContract.Child.MainChild(main(componentContext))
        }

    private fun main(componentContext: ComponentContext): MainContract {
        return MainComponent(
            componentContext = componentContext,
            onBottomBarItem = {
                // TODO
            }
        )
    }

    private sealed interface Config : Parcelable {
        @Parcelize
        object Main : Config
    }
}
