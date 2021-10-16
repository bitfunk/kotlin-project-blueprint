package eu.upwolf.mobile.blueprint.feature.home

actual class Platform actual constructor() {
    actual val platformName: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}
