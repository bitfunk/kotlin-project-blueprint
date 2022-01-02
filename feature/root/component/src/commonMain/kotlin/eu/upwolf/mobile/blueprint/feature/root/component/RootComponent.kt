/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.replaceCurrent
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import eu.upwolf.mobile.blueprint.feature.root.component.RootComponentContract.Child
import eu.upwolf.mobile.blueprint.feature.root.component.RootComponentContract.Config
import eu.upwolf.mobile.blueprint.feature.home.component.HomeComponent
import eu.upwolf.mobile.blueprint.feature.splash.component.SplashComponent

class RootComponent(
    componentContext: ComponentContext,
) : RootComponentContract.Component, ComponentContext by componentContext {

    private val router = router<Config, Child>(
        initialConfiguration = Config.Splash,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override val routerState: Value<RouterState<*, Child>> = router.state

    private fun createChild(config: Config, componentContext: ComponentContext): RootComponentContract.Child =
        when (config) {
            is Config.Splash -> splash(componentContext, config)
            is Config.Home -> home(componentContext, config)
        }

    private fun splash(componentContext: ComponentContext, config: Config.Splash): Child.Splash {
        return Child.Splash(SplashComponent(
            componentContext = componentContext,
            onFinishedAction = {
                router.replaceCurrent(Config.Home)
            }
        ))
    }

    private fun home(componentContext: ComponentContext, config: Config.Home): Child.Home {
        return Child.Home(HomeComponent(
            componentContext = componentContext,
            onFinishedAction = {
                router.replaceCurrent(Config.Splash)
            }
        ))
    }
}
