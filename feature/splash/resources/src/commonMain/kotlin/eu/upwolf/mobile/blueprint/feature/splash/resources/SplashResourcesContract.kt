/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.splash.resources

import dev.icerock.moko.resources.StringResource

interface SplashResourcesContract {

    interface Resources {
        val strings: Strings
    }

    interface Strings {
        fun logoContentDescription(): StringResource
    }
}
