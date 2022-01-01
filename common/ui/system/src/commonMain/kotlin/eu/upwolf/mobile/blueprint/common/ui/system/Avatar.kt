/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.ui.system

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import eu.upwolf.mobile.blueprint.common.ui.theme.AppTheme

object Avatar {
    sealed class Size {
        @get:Composable
        abstract val value: Dp

        object Small : Size() {
            override val value: Dp
                @Composable
                get() = AppTheme.size.medium
        }

        object Medium : Size() {
            override val value: Dp
                @Composable
                get() = AppTheme.size.large
        }

        object Large : Size() {
            override val value: Dp
                @Composable
                get() = AppTheme.size.larger
        }
    }
}

@Composable
fun Avatar(
    painter: Painter,
    size: Avatar.Size = Avatar.Size.Medium,
) {
    Box(
        modifier = Modifier
            .size(size.value)
            .clip(CircleShape)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
