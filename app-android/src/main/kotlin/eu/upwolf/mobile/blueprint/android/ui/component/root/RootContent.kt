/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.android.ui.component.root

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.crossfadeScale

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootContent(
    root: RootContract,
    // modifier: Modifier = Modifier
) {
    Children(
        routerState = root.routerState,
        animation = crossfadeScale()
    ) {
        when (val child = it.instance) {
            is RootContract.Child.MainChild -> Text("main component")
        }
    }
}
