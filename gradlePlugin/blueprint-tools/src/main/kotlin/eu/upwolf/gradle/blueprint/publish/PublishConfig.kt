/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.publish

import eu.upwolf.gradle.blueprint.dependency.byEnv
import eu.upwolf.gradle.blueprint.dependency.byProperty
import org.gradle.api.Project

// TODO groupId needed?
object PublishConfig {
    const val description = "A template for Kotlin Android projects"
    const val year = "2021"

    // GitHub
    const val gitHubOwner = "wmontwe"
    const val gitHubRepository = "mobile-project-blueprint"

    // URL
    const val host = "github.com"
    const val path = "$gitHubOwner/$gitHubRepository"
    const val url = "https://$host/$path"

    // LICENSE
    const val licenseName = "Copyright"
    const val licenseUrl = "$url/blob/main/LICENSE"
    const val licenseDistribution = "repo"

    // DEVELOPER
    const val developerId = "wmontwe"
    const val developerName = "Wolf-Martell Montwé"

    // SCM
    const val scmConnection = "scm:git:git://$host/$path.git"
    const val scmDeveloperConnection = "scm:git:ssh://$host/$path.git"
    const val scmUrl = url

    // Issues
    const val issueSystem = "GitHub Issues"
    const val issueUrl = "$url/issues"

    // GitHub Maven Repository
    const val gitHubPackageRegistryName = "GitHubPackages"
    const val gitHubPackageRegistryUrl = "https://maven.pkg.github.com/$gitHubOwner/$gitHubRepository"

    private const val GITHUB_PACKAGE_UPLOAD_USER_PROPERTY_NAME = "githubPackageUpload.user"
    private const val GITHUB_PACKAGE_UPLOAD_PASSWORD_PROPERTY_NAME = "githubPackageUpload.password"

    private const val GITHUB_PACKAGE_UPLOAD_USER_ENV_NAME = "GITHUB_PACKAGE_UPLOAD_USER"
    private const val GITHUB_PACKAGE_UPLOAD_PASSWORD_ENV_NAME = "GITHUB_PACKAGE_UPLOAD_KEY"

    fun loadGitHubPackageUser(project: Project): String? {
        return GITHUB_PACKAGE_UPLOAD_USER_PROPERTY_NAME.byProperty(project)
            ?: GITHUB_PACKAGE_UPLOAD_USER_ENV_NAME.byEnv()
    }

    fun loadGitHubPackagePassword(project: Project): String? {
        return GITHUB_PACKAGE_UPLOAD_PASSWORD_PROPERTY_NAME.byProperty(project)
            ?: GITHUB_PACKAGE_UPLOAD_PASSWORD_ENV_NAME.byEnv()
    }
}
