/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.splash.resources

import dev.icerock.moko.resources.desc.StringDesc

object SplashResources : SplashResourcesContract.Resources {
    override val strings = Strings

    object Strings : SplashResourcesContract.Strings {
        override fun logoContentDescription(): StringDesc =
            StringDesc.Resource(MR.strings.logo_content_description)
    }
}
