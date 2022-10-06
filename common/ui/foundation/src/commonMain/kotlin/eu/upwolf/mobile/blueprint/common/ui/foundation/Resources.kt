/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.ui.foundation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import dev.icerock.moko.resources.StringResource

@Composable
expect fun painterResource(resourcePath: String): Painter

@Composable
expect fun stringResource(id: StringResource): String
