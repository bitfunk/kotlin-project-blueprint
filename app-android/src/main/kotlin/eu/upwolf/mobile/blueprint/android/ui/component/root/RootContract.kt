/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.android.ui.component.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import eu.upwolf.mobile.blueprint.android.ui.component.main.MainContract

interface RootContract {

    val routerState: Value<RouterState<*, Child>>

    sealed interface Child {
        class MainChild(val main: MainContract) : Child
    }
}
