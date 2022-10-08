/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.splash.resources

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.image.ImageDesc

object SplashResources : SplashResourcesContract.Resources {
    override val strings = Strings
    override val images = Images

    object Strings : SplashResourcesContract.Strings {
        override fun logoContentDescription(): StringResource = MR.strings.logo_content_description
    }

    object Images : SplashResourcesContract.Images {
        override fun logo(): ImageDesc = TODO()
            // ImageDesc.Resource(MR.images)
    }
}
