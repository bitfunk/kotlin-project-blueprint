/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.ui.foundation.painter

import android.content.ComponentCallbacks2
import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

/**
 * Taken from androidx.compose.ui.platform.AndroidCompositionLocals.android.kt
 */

internal val LocalImageVectorCache = staticCompositionLocalOf<ImageVectorCache> {
    noLocalProvidedFor("LocalImageVectorCache")
}

@Composable
fun ProvideImageVectorCache(
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    var configuration by remember {
        mutableStateOf(
            context.resources.configuration,
            neverEqualPolicy()
        )
    }
    val imageVectorCache = obtainImageVectorCache(context, configuration)
    CompositionLocalProvider(
        LocalImageVectorCache provides imageVectorCache
    ) {
        content()
    }
}

@Stable
@Composable
private fun obtainImageVectorCache(
    context: Context,
    configuration: Configuration?
): ImageVectorCache {
    val imageVectorCache = remember { ImageVectorCache() }
    var currentConfiguration = remember { configuration }
    val callbacks = remember {
        object : ComponentCallbacks2 {
            override fun onConfigurationChanged(configuration: Configuration) {
                // If there is no configuration, assume all flags have changed.
                val changedFlags = currentConfiguration?.updateFrom(configuration) ?: -0x1
                imageVectorCache.prune(changedFlags)
                currentConfiguration = configuration
            }

            override fun onLowMemory() {
                imageVectorCache.clear()
            }

            override fun onTrimMemory(level: Int) {
                imageVectorCache.clear()
            }
        }
    }
    DisposableEffect(imageVectorCache) {
        context.applicationContext.registerComponentCallbacks(callbacks)
        onDispose {
            context.applicationContext.unregisterComponentCallbacks(callbacks)
        }
    }
    return imageVectorCache
}

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}
