/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import eu.upwolf.mobile.blueprint.desktop.ui.MainApp
import eu.upwolf.mobile.blueprint.feature.root.component.RootComponent
import eu.upwolf.mobile.blueprint.feature.root.component.RootComponentContract
import eu.upwolf.mobile.blueprint.feature.root.ui.RootContent
import kotlin.system.exitProcess

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()
    val root = createRootComponent(DefaultComponentContext(lifecycle))

    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            title = "Blueprint",
            state = windowState
        ) {
            MainApp {
                RootContent(root)
            }
        }
    }
    exitProcess(0)
}

private fun createRootComponent(componentContext: ComponentContext): RootComponentContract.Component {
    return RootComponent(
        componentContext = componentContext
    )
}
