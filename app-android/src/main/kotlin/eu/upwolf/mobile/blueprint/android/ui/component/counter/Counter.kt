/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.android.ui.component.counter

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce

class Counter {
    private val value = MutableValue(State())

    val state: Value<State> = value

    fun increment() {
        value.reduce { it.copy(count = it.count + 1) }
    }

    data class State(val count: Int = 0)
}
