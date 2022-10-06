/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.feature.splash.resources

import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.image.ImageDesc
import dev.icerock.moko.resources.desc.image.Resource

object SplashResources : SplashResourcesContract.Resources {
    override val strings = Strings
    override val images = Images

    object Strings : SplashResourcesContract.Strings {
        override fun logoContentDescription(): StringDesc =
            StringDesc.Resource(MR.strings.logo_content_description)
    }

    object Images : SplashResourcesContract.Images {
        override fun logo(): ImageDesc = TODO()
            // ImageDesc.Resource(MR.images)
    }
}
