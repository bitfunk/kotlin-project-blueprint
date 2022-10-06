/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.splash.resources

import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.image.ImageDesc

interface SplashResourcesContract {

    interface Resources {
        val strings: Strings
        val images: Images
    }

    interface Strings {
        fun logoContentDescription(): StringDesc
    }

    interface Images {
        fun logo(): ImageDesc
    }
}
