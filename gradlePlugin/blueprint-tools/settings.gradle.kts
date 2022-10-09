/*
* Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
*/

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

rootProject.name = "blueprint-tools"
