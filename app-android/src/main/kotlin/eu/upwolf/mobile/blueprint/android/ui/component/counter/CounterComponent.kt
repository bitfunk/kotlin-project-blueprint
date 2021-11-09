/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.android.ui.component.counter

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState

@Composable
fun CounterUi(counter: Counter) {
    val state by counter.state.subscribeAsState()

    Column {
        Text(
            text = state.count.toString()
        )

        Button(onClick = counter::increment) {
            Text(text = "Increment")
        }
    }
}
