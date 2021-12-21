/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../../gradle/libs.versions.toml"))
        }
    }
}
