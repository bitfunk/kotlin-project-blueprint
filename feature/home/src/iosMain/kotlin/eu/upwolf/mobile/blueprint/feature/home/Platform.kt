package eu.upwolf.mobile.blueprint.feature.home

import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val platformName: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}
