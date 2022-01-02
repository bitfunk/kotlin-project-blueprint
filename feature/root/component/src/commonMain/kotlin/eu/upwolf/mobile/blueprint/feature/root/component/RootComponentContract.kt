/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.root.component

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import eu.upwolf.mobile.blueprint.feature.home.component.HomeContract
import eu.upwolf.mobile.blueprint.feature.splash.component.SplashContract

interface RootComponentContract {

    interface Component {
        val routerState: Value<RouterState<*, Child>>
    }

    sealed class Child {
        class Splash(val component: SplashContract.Component) : Child()
        class Home(val component: HomeContract.Component) : Child()
    }

    sealed class Config : Parcelable {
        @Parcelize
        object Splash : Config()

        @Parcelize
        object Home : Config()
    }
}
