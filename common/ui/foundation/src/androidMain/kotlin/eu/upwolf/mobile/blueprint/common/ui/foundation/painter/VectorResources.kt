/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.ui.foundation.painter

import android.content.res.Configuration
import android.content.res.Resources
import android.content.res.XmlResourceParser
import android.util.Xml
import androidx.compose.ui.graphics.vector.ImageVector
import org.xmlpull.v1.XmlPullParserException
import java.lang.ref.WeakReference

/**
 * Taken from androidx.compose.ui.res.VectorResources.android.kt
 *
 * Modified to load from assets by file name
 */

@Throws(XmlPullParserException::class)
@SuppressWarnings("RestrictedApi")
internal fun loadVectorResourceInner(
    theme: Resources.Theme? = null,
    res: Resources,
    parser: XmlResourceParser
): ImageVectorCache.ImageVectorEntry {
    val attrs = Xml.asAttributeSet(parser)
    val resourceParser = AndroidVectorParser(parser)
    val builder = resourceParser.createVectorImageBuilder(res, theme, attrs)

    var nestedGroups = 0
    while (!parser.isAtEnd()) {
        nestedGroups = resourceParser.parseCurrentVectorNode(
            res,
            attrs,
            theme,
            builder,
            nestedGroups
        )
        parser.next()
    }
    return ImageVectorCache.ImageVectorEntry(builder.build(), resourceParser.config)
}

internal class ImageVectorCache {
    /**
     * Key that binds the corresponding theme with the resource identifier for the vector asset
     */
    data class Key(
        val theme: Resources.Theme,
        val id: String
    )

    /**
     * Tuple that contains the [ImageVector] as well as the corresponding configuration flags
     * that the [ImageVector] depends on. That is if there is a configuration change that updates
     * the parameters in the flag, this vector should be regenerated from the current configuration
     */
    data class ImageVectorEntry(
        val imageVector: ImageVector,
        val configFlags: Int
    )

    private val map = HashMap<Key, WeakReference<ImageVectorEntry>>()

    operator fun get(key: Key): ImageVectorEntry? = map[key]?.get()

    fun prune(configChanges: Int) {
        val it = map.entries.iterator()
        while (it.hasNext()) {
            val entry = it.next()
            val imageVectorEntry = entry.value.get()
            if (imageVectorEntry == null ||
                Configuration.needNewResources(configChanges, imageVectorEntry.configFlags)
            ) {
                it.remove()
            }
        }
    }

    operator fun set(key: Key, imageVectorEntry: ImageVectorEntry) {
        map[key] = WeakReference<ImageVectorEntry>(imageVectorEntry)
    }

    fun clear() {
        map.clear()
    }
}
