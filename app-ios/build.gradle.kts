/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradleBlueprintConfigurationIosApp)
}

apple {
    iosApp {
        productName = "Blueprint"

        dependencies {
            implementation(project(":feature:home"))
        }
    }
}
