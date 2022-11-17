/*
 * ISC License
 *
 * Copyright (c) 2022. Wolf-Martell Montwé (bitfunk)
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
 * REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
 * LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
 * OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */

package eu.upwolf.gradle.blueprint.version

import eu.upwolf.gradle.gitversion.VersionDetails
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.provideDelegate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class VersionConfigurationPlugin : Plugin<Project> {

    private lateinit var project: Project

    override fun apply(target: Project) {
        project = target

        target.apply(plugin = "eu.upwolf.git-version")

        target.tasks.register("versionInfo") {
            group = "versioning"

            doLast {
                println("VersionName: ${generateVersionName()}")
                println("VersionCode: ${generateVersionCode()}")
                println("VersionCodeFeature: ${generateFeatureVersionCode()}")
                println("VersionDetails: ${generateVersionDetails()}")
            }
        }

        target.allprojects {
            version = generateVersionName()
            extra.set("versionCode", generateVersionCode())
            extra.set("versionCodeFeature", generateFeatureVersionCode())
        }
    }

    private fun loadVersionDetails(): VersionDetails {
        val versionDetails: groovy.lang.Closure<VersionDetails> by project.extra
        return versionDetails()
    }

    fun generateVersionCode(): Int {
        val details = loadVersionDetails()

        return details.versionCode * 100 + details.commitDistance
    }

    fun generateFeatureVersionCode(): Int {
        val timestamp = SimpleDateFormat("MMddHHmm", Locale.ENGLISH).format(Date())
        return timestamp.toInt()
    }

    private fun generateVersionDetails(): String {
        val details = loadVersionDetails()

        return """
            VersionDetails(
               version = ${details.version}
               versionCode = ${details.versionCode}
               gitHash = ${details.gitHash}
               gitHashFull = ${details.gitHashFull}
               branchName = ${details.branchName}
               commitDistance = ${details.commitDistance}
               lastTag = ${details.lastTag}
               isClean = ${details.isCleanTag}
            )
        """.trimIndent()
    }

    fun generateVersionName(): String {
        val details = loadVersionDetails()

        return when {
            details.branchName == null -> versionNameWithQualifier(details)
            patternNoQualifierBranch.matches(details.branchName) -> versionNameWithQualifier(details)
            patternFeatureBranch.matches(details.branchName) -> versionNameFeature(details)
            patternDependabotBranch.matches(details.branchName) -> versionNameDependabot(details)
            patternRenovateBranch.matches(details.branchName) -> versionNameRenovate(details)
            else -> throw UnsupportedOperationException("branch name not supported: ${details.branchName}")
        }
    }

    private fun versionNameFeature(details: VersionDetails): String {
        var featureName = patternFeatureBranch.matchEntire(details.branchName)!!.groups[1]!!.value

        if (patternIssueNumber.matches(featureName)) {
            featureName = patternIssueNumber.matchEntire(featureName)!!.groups[1]!!.value
        }

        return versionNameWithQualifier(details, featureName)
    }

    private fun versionNameDependabot(details: VersionDetails): String {
        var dependabotName = patternDependabotBranch.matchEntire(details.branchName)!!.groups[1]!!.value

        dependabotName = dependabotName
            .replace("_", "-")
            .replace("/", "-")

        return versionNameWithQualifier(details, "bump-$dependabotName")
    }

    private fun versionNameRenovate(details: VersionDetails): String {
        var renovateName = patternRenovateBranch.matchEntire(details.branchName)!!.groups[1]!!.value

        renovateName = renovateName
            .replace("_", "-")
            .replace("/", "-")

        if (renovateName == "configure") {
            renovateName = "renovate-$renovateName"
        } else {
            renovateName = "renovate-bump-$renovateName"
        }

        return versionNameWithQualifier(details, renovateName)
    }

    private fun versionNameWithQualifier(details: VersionDetails, name: String = ""): String {
        val version = if (!details.isCleanTag) {
            var versionCleaned = details.version.substringBefore(".dirty")
            if (details.commitDistance > 0) {
                versionCleaned = versionCleaned.substringBefore("-")
            }
            if (name.isBlank()) {
                "$versionCleaned-SNAPSHOT"
            } else {
                "$versionCleaned-$name-SNAPSHOT"
            }
        } else {
            details.version
        }

        return version.substringAfter("v")
    }

    companion object {
        val patternNoQualifierBranch = "main|release/.*".toRegex()
        val patternFeatureBranch = "feature/(.*)".toRegex()
        val patternDependabotBranch = "dependabot/(.*)".toRegex()
        val patternRenovateBranch = "renovate/(.*)".toRegex()
        val patternIssueNumber = "[A-Z]{2,8}-.*/(.*)".toRegex()
    }
}

fun Project.version(): String = this.version as String
fun Project.versionCleaned(): String {
    return if (version().endsWith("-SNAPSHOT")) {
        version().split("-")[0]
    } else {
        version()
    }
}

fun Project.versionCode(): Int = this.extra.get("versionCode") as Int
fun Project.versionCodeFeature(): Int = this.extra.get("versionCodeFeature") as Int
