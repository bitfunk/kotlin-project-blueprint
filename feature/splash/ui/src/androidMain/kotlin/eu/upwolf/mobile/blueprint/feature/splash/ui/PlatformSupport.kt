/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.splash.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import eu.upwolf.mobile.blueprint.common.ui.foundation.drawableId

@Composable
actual fun painterResource(resourcePath: String): Painter {
    val id = drawableId(resourcePath, R.drawable::class.java)
    return androidx.compose.ui.res.painterResource(id = id)
}
