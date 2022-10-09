/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.ui.foundation.painter

import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext

/**
 * Taken from androidx.compose.ui.res.PainterResources.android.kt
 *
 * Modified to load from assets by file name
 */

@Composable
fun painterResource(resourcePath: String): Painter {
    val context = LocalContext.current
    val path = resourcePath.substringBeforeLast("/")
    val name = resourcePath.substringAfterLast("/")

    return if (resourcePath.endsWith(".xml")) {
        val imageVector = loadVectorResource(context.theme, context.resources, context.assets, name)
        rememberVectorPainter(imageVector)
    } else {
        val imageBitmap = remember(path, name) {
            loadImageBitmapResource(context.assets, name)
        }
        BitmapPainter(imageBitmap)
    }
}

@Composable
private fun loadVectorResource(
    theme: Resources.Theme,
    res: Resources,
    assets: AssetManager,
    fileName: String
): ImageVector {
    val imageVectorCache = LocalImageVectorCache.current
    val key = ImageVectorCache.Key(theme, fileName)
    var imageVectorEntry = imageVectorCache[key]
    if (imageVectorEntry == null) {
        @Suppress("ResourceType") val parser = assets.openXmlResourceParser(fileName)
        if (parser.seekToStartTag().name != "vector") {
            throw IllegalArgumentException(errorMessage)
        }
        imageVectorEntry = loadVectorResourceInner(theme, res, parser)
        imageVectorCache[key] = imageVectorEntry
    }
    return imageVectorEntry.imageVector
}

/**
 * Helper method to validate the asset resource is a supported resource type and returns
 * an ImageBitmap resource. Because this throws exceptions we cannot have this implementation
 * as part of the composable implementation it is invoked in.
 */
private fun loadImageBitmapResource(assets: AssetManager, fileName: String): ImageBitmap {
    return try {
        with(assets.open(fileName)) {
            BitmapFactory.decodeStream(this).asImageBitmap()
        }
    } catch (throwable: Throwable) {
        throw IllegalArgumentException(errorMessage)
    }
}

private const val errorMessage =
    "Only VectorDrawables and rasterized asset types are supported ex. PNG, JPG"
