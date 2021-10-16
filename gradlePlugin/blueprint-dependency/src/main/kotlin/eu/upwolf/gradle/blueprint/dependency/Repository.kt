/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.dependency

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler

object Repository {
    private const val gitHubOrgUpWolf = "upwolf"

    val github = listOf(
        // GitHub organization, GitHub repository name, Maven dependency group
        listOf(gitHubOrgUpWolf, "gradle-git-version", "eu.upwolf.gradle.gitversion")
    )
}

fun RepositoryHandler.gitHub(project: Project) {
    Repository.github.forEach { (organization, repository, group) ->
        maven {
            setUrl("https://maven.pkg.github.com/$organization/$repository")
            credentials {
                username = "githubPackageDownload.user".byProperty(project) ?: "GITHUB_PACKAGE_DOWNLOAD_USER".byEnv()
                password = "githubPackageDownload.key".byProperty(project) ?: "GITHUB_PACKAGE_DOWNLOAD_KEY".byEnv()
            }
            content {
                includeGroup(group)
            }
        }
    }
}
