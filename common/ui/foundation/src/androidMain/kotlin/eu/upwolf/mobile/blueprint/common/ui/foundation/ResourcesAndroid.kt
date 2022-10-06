/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.ui.foundation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import dev.icerock.moko.resources.StringResource

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
fun drawableId(resourcePath: String, drawableClass: Class<*>): Int {
    val imageName = resourcePath.substringAfterLast("/").substringBefore(".")
    val field = drawableClass.getDeclaredField(imageName)
    val idValue = field.get(drawableClass) as Integer
    return idValue.toInt()
}

@Composable
actual fun painterResource(resourcePath: String): Painter =
    eu.upwolf.mobile.blueprint.common.ui.foundation.painter.painterResource(resourcePath)

@Composable
actual fun stringResource(id: StringResource): String = androidx.compose.ui.res.stringResource(id.resourceId)
