/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.splash.resources

import dev.icerock.moko.resources.StringResource

object SplashResources : SplashResourcesContract.Resources {
    override val strings = Strings

    object Strings : SplashResourcesContract.Strings {
        override fun logoContentDescription(): StringResource = MR.strings.logo_content_description
    }
}
