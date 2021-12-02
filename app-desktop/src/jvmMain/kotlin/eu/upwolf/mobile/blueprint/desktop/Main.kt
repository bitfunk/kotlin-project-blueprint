/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.desktop

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import eu.upwolf.mobile.blueprint.desktop.ui.MainApp
import kotlin.system.exitProcess

fun main() {
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Blueprint",
            state = WindowState(size = DpSize(1440.dp, 768.dp))
        ) {
            MainApp()
        }
    }
    exitProcess(0)
}
