/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.splash.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import eu.upwolf.mobile.blueprint.common.ui.foundation.stringResource
import eu.upwolf.mobile.blueprint.common.ui.theme.AppTheme
import eu.upwolf.mobile.blueprint.feature.splash.resources.SplashResources
import kotlinx.coroutines.delay

private const val DELAY = 3000L

@Composable
expect fun painterResource(resourcePath: String): Painter

@Composable
fun SplashPage(
    onFinished: () -> Unit
) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessMediumLow
            )
        )
        delay(DELAY)
        onFinished()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource("drawable/splash_logo.xml"),
            contentDescription = stringResource(SplashResources.Strings.logoContentDescription()),
            modifier = Modifier
                .size(AppTheme.size.larger)
                .scale(scale.value)
        )
    }
}
