/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

@file:Suppress("UnstableApiUsage")

package eu.upwolf.gradle.blueprint.dependency

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

abstract class VersionCatalogHelper(
    project: Project
) {
    private val catalogs: VersionCatalogsExtension = project.extensions.getByType()
    protected val libs: VersionCatalog = catalogs.named("libs")
}

class DependencyHelper(
    project: Project
) : VersionCatalogHelper(project), Deps {

    private fun findDependency(name: String): String {
        return libs.findDependency(name).get().get().toString()
    }

    override val kotlin: Deps.Kotlin
        get() = object : Deps.Kotlin {
            override val core: String
                get() = findDependency("kotlin-core")
            override val android: String
                get() = findDependency("kotlin-android")
        }

    override val kotlinx: Deps.KotlinX
        get() = object : Deps.KotlinX {
            override val coroutines: Deps.KotlinX.Coroutines
                get() = object : Deps.KotlinX.Coroutines {
                    override val core: String
                        get() = findDependency("kotlinx-coroutines-core")
                    override val android: String
                        get() = findDependency("kotlinx-coroutines-android")
                }
        }

    override val androidx: Deps.AndroidX
        get() = object : Deps.AndroidX {
            override val compose: Deps.AndroidX.Compose
                get() = object : Deps.AndroidX.Compose {
                    override val compiler: String
                        get() = findDependency("androidx-compose-compiler")
                    override val runtime: String
                        get() = findDependency("androidx-compose-runtime")
                    override val foundation: String
                        get() = findDependency("androidx-compose-foundation")
                    override val ui: String
                        get() = findDependency("androidx-compose-ui")
                    override val uiTooling: String
                        get() = findDependency("androidx-compose-ui-uiTooling")
                    override val uiToolingPreview: String
                        get() = findDependency("androidx-compose-ui-uiToolingPreview")
                    override val material: String
                        get() = findDependency("androidx-compose-material")
                    override val material3: String
                        get() = findDependency("androidx-compose-material3")
                }
        }
    override val decompose: Deps.Decompose
        get() = object : Deps.Decompose {
            override val core: String
                get() = findDependency("decompose-core")
        }

    override val koin: Deps.Koin
        get() = object : Deps.Koin {
            override val core: String
                get() = findDependency("koin-core")
            override val test: String
                get() = findDependency("koin-test")
        }

    override val test: Deps.Test
        get() = object : Deps.Test {
            override val junit: String
                get() = findDependency("test-junit")
            override val kotlin: Deps.Test.Kotlin
                get() = object : Deps.Test.Kotlin {
                    override val core: String
                        get() = findDependency("test-kotlin-core")
                    override val annotations: String
                        get() = findDependency("test-kotlin-annotations")
                    override val junit: String
                        get() = findDependency("test-kotlin-junit")
                }
        }
}

class VersionHelper(
    project: Project
) : VersionCatalogHelper(project), Versions {

    private fun findVersion(name: String): String {
        return libs.findVersion(name).get().requiredVersion
    }

    override val androidX: Versions.AndroidX
        get() = object : Versions.AndroidX {
            override val compose: Versions.AndroidX.Compose
                get() = object : Versions.AndroidX.Compose {
                    override val compiler: String
                        get() = findVersion("androidx-compose-compiler")
                }
        }
}

interface Deps {
    val kotlin: Kotlin

    interface Kotlin {
        val core: String
        val android: String
    }

    val kotlinx: KotlinX

    interface KotlinX {

        val coroutines: Coroutines

        interface Coroutines {
            val core: String
            val android: String
        }
    }

    val androidx: AndroidX

    interface AndroidX {

        val compose: Compose

        interface Compose {
            val compiler: String
            val runtime: String
            val foundation: String
            val ui: String
            val uiTooling: String
            val uiToolingPreview: String
            val material: String
            val material3: String
        }
    }

    val decompose: Decompose

    interface Decompose {
        val core: String
    }

    val koin: Koin

    interface Koin {
        val core: String
        val test: String
    }

    val test: Test

    interface Test {
        val junit: String

        val kotlin: Kotlin

        interface Kotlin {
            val core: String
            val annotations: String
            val junit: String
        }
    }
}

interface Versions {

    val androidX: AndroidX

    interface AndroidX {

        val compose: Compose

        interface Compose {
            val compiler: String
        }
    }
}
