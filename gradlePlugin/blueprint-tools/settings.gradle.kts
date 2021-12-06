/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    includeBuild("../blueprint-dependency")
}

includeBuild("../blueprint-dependency") {
    dependencySubstitution {
        substitute(module("eu.upwolf.gradle.blueprint.dependency:blueprint-dependency")).using(project(":"))
    }
}
