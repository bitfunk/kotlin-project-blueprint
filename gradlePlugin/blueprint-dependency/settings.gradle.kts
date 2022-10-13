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

rootProject.name = "blueprint-dependency"
