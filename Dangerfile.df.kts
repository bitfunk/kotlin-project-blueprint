/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

import systems.danger.kotlin.danger
import systems.danger.kotlin.fail
import systems.danger.kotlin.onGitHub
import systems.danger.kotlin.warn

const val regexFeatureBranch =
    "(?:feature\\/(?:[A-Z]{2,8}-\\d{1,6}\\/)?(?:add|change|remove|fix|bump|security)-[a-z0-9-.]*)"
        .toRegex()
const val regexReleaseBranch =
    "(?:release\\/(?:\\d{1,3}\\.\\d{1,3}(?:\\.\\d{1,3})?)(?:\\/prepare-\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})?)"
        .toRegex()
const val regexDependabotBranch =
    "dependabot/(.*)"
        .toRegex()

const val regexFeatureTitle =
    "(?:(?:\\[[A-Z]{2,8}-\\d{1,6}\\]\\s)?(?:Add|Change|Remove|Fix|Bump|Security)\\s.*)"
        .toRegex()
const val regexReleaseTitle =
    "(?:(?:Prepare )?Release \\d{1,3}\\.\\d{1,3}\\.\\d{1,3})"
        .toRegex()

const val lineCountInform = 300
const val lineCountWarn = 600
const val lineCountFail = 1200

const val pullRequestBodyMinLines = 20

danger(args) {
    val allSourceFiles = git.modifiedFiles + git.createdFiles
    val isChangelogUpdated = allSourceFiles.contains("CHANGELOG.adoc")

    onGitHub {
        val branchName = pullRequest.head.label.substringAfter(":")
        val isFeatureBranch = regexFeatureBranch.matches(branchName)
        val isReleaseBranch = regexReleaseBranch.matches(branchName)
        val isDependabotBranch = regexDependabotBranch.matches(branchName)
        val isFeatureTitle = regexFeatureTitle.matches(pullRequest.title)
        val isReleaseTitle = regexReleaseTitle.matches(pullRequest.title)

        if (!isFeatureBranch && !isReleaseBranch && !isDependabotBranch) {
            fail(
                "Branch name is not following our pattern:\n" +
                    "\nrelease/1.2(.3)(/prepare-1.2.3)\n" +
                    "\nfeature/(ISSUE-123)/add|change|remove|fix|bump|security-feature-title\n" +
                    "\n\n" +
                    "\n Current name: $branchName"
            )
        }

        if (isFeatureBranch) {
            if (!isFeatureTitle) {
                fail(
                    "Title is not following our pattern:\n" +
                        "\n[issue_id](optional) Add|Change|Remove|Fix|Bump|Security {Feature title}"
                )
            }
        }

        if (isReleaseBranch) {
            if (!isReleaseTitle) {
                fail(
                    "Title is not following our pattern: Prepare Release major.minor.patch (1.2.0)"
                )
            }
        }

        // General
        if (pullRequest.assignee == null) {
            warn("Please assign someone to merge this PR")
        }

        if (pullRequest.milestone == null) {
            warn("Set a milestone please")
        }

        if (pullRequest.body.length < pullRequestBodyMinLines) {
            warn("Please include a description of your PR changes")
        }

        // Changelog
        if (isChangelogUpdated) {
            warn("Changes should be reflected in the CHANGELOG.adoc")
        }

        // Size
        val changes = (pullRequest.additions ?: 0) - (pullRequest.deletions ?: 0)
        if (changes > lineCountFail) {
            fail("This Pull-Request is way to big, please slice it into smaller pull-requests.")
        } else if (changes > lineCountWarn) {
            warn("Too Big Pull-Request, keep changes smaller!")
        } else if (changes > lineCountInform) {
            info("Large Pull-Request, try to keep changes smaller if you can")
        }
    }
}
