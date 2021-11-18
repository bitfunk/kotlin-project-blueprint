/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.23.0"
}

refreshVersions {
    versionsPropertiesFile = File("${settingsDir.parentFile.parent}/config/refreshVersions/versions.properties")
}
