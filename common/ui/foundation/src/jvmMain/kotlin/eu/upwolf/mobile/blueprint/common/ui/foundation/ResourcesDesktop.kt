/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.ui.foundation

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.StringResource

@Composable
actual fun stringResource(id: StringResource): String = id.localized()
