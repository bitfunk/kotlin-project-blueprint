/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.defaultComponentContext
import eu.upwolf.mobile.blueprint.android.ui.MainApp
import eu.upwolf.mobile.blueprint.feature.root.component.RootComponent
import eu.upwolf.mobile.blueprint.feature.root.component.RootComponentContract
import eu.upwolf.mobile.blueprint.feature.root.ui.RootContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val root = createRootComponent(defaultComponentContext())

        setContent {
            MainApp {
                RootContent(component = root)
            }
        }
    }

    private fun createRootComponent(componentContext: ComponentContext): RootComponentContract.Component {
        return RootComponent(
            componentContext = componentContext
        )
    }
}
