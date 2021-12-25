/*
* Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
*/

@file:Suppress("UnstableApiUsage")

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../../gradle/libs.versions.toml"))
        }
    }
}

pluginManagement {
    includeBuild("../blueprint-dependency")
}

includeBuild("../blueprint-dependency") {
    dependencySubstitution {
        substitute(module("eu.upwolf.gradle.blueprint.dependency:blueprint-dependency")).using(project(":"))
    }
}
