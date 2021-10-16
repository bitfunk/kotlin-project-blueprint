/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.gradle.blueprint.publish

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublicationContainer
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.SigningExtension

// TODO setup Dokka
// TODO bundle with library config plugin
class PublishConfigurationPlugin : Plugin<Project> {

    private lateinit var project: Project

    override fun apply(target: Project) {
        project = target

        target.apply(plugin = "maven-publish")
        target.apply(plugin = "signing")

        setupPublishing(target)
        setupSigning(target)
    }

    private fun setupPublishing(project: Project) {
        project.afterEvaluate {
            publishing {
                repositories {
                    maven {
                        name = PublishConfig.gitHubPackageRegistryName
                        setUrl(PublishConfig.gitHubPackageRegistryUrl)
                        credentials {
                            username = PublishConfig.loadGitHubPackageUser(project)
                            password = PublishConfig.loadGitHubPackagePassword(project)
                        }
                    }
                }

                publications {
                    withType<MavenPublication> {
                        pom {
                            url.set(PublishConfig.url)
                            description.set(PublishConfig.description)
                            inceptionYear.set(PublishConfig.year)

                            licenses {
                                license {
                                    name.set(PublishConfig.licenseName)
                                    url.set(PublishConfig.licenseUrl)
                                    distribution.set(PublishConfig.licenseDistribution)
                                }
                            }

                            developers {
                                developer {
                                    id.set(PublishConfig.developerId)
                                    name.set(PublishConfig.developerName)
                                }
                            }

                            scm {
                                connection.set(PublishConfig.scmConnection)
                                developerConnection.set(PublishConfig.scmDeveloperConnection)
                                url.set(PublishConfig.scmUrl)
                            }

                            issueManagement {
                                system.set(PublishConfig.issueSystem)
                                url.set(PublishConfig.issueUrl)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupSigning(project: Project) {
        if (SigningConfig.isSigningEnabled) {
            var publicationsAll: PublicationContainer? = null
            project.publishing {
                publicationsAll = publications
            }

            project.signing {
                useInMemoryPgpKeys(
                    SigningConfig.loadSigningKey(project),
                    SigningConfig.loadSigningPassword(project)
                )
                sign(publicationsAll)
            }
        }
    }

    private fun Project.publishing(action: Action<PublishingExtension>) {
        extensions.configure(PublishingExtension::class.java, action)
    }

    private fun Project.signing(action: Action<SigningExtension>) {
        extensions.configure(SigningExtension::class.java, action)
    }
}
