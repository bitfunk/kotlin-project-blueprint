object Versions {
    const val kotlin = "1.2.71"

    object GradlePlugin {
        const val kotlin = Versions.kotlin
        const val android = "3.3.0-alpha13"
        const val googleServices = "3.1.2"
    }

    const val androidKtx = "1.0.0"
    const val androidX = "1.0.0"
    const val androidXConstraintLayout = "2.0.0-alpha2"
    const val androidXLifeCycle = "2.0.0"

    const val androidNavigation = "1.0.0-alpha06"

    const val googleMaterial = "1.0.0"


    const val testJUnit = "4.12"

    const val androidTestX = "1.1.0-beta01"
    const val androidTestXEspresso = "3.1.0-beta01"
    const val androidTestXUiAutomator = "2.2.0-beta01"

    // https://github.com/agoda-com/Kakao
    // currently patched version for AndroidX from https://github.com/wmontwe/Kakao
    const val androidTestKakao = "1.4.0-androidx"
}

object GradlePlugins {
    val android = "com.android.tools.build:gradle:${Versions.GradlePlugin.android}"
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.GradlePlugin.kotlin}"
    val googleServicesGradlePlugin = "com.google.gms:google-services:${Versions.GradlePlugin.googleServices}"
}


object Libraries {
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val kotlinStdLibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val androidKtx = "androidx.core:core-ktx:${Versions.androidKtx}"

    val androidXAppCompat = "androidx.appcompat:appcompat:${Versions.androidX}"
    val androidXAnnotations = "androidx.annotation:annotation:${Versions.androidX}"
    val androidXBrowser = "androidx.browser:browser:${Versions.androidX}"
    val androidXConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidXConstraintLayout}"
    val androidXLifecyleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.androidXLifeCycle}"
    val androidXLifecyleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.androidXLifeCycle}"
    val androidXRecyclerView = "androidx.recyclerview:recyclerview:${Versions.androidX}"

    val androidNavigationFragmentKtx = "android.arch.navigation:navigation-fragment-ktx:${Versions.androidNavigation}"
    val androidNavigationUiKtx = "android.arch.navigation:navigation-ui-ktx:${Versions.androidNavigation}"

    // http://developer.android.com/tools/extras/support-library.html
    val googleMaterialComponents = "com.google.android.material:material:${Versions.googleMaterial}"


    val testJunit = "junit:junit:${Versions.testJUnit}"


    val androidTestXRunner = "androidx.test:runner:${Versions.androidTestX}"
    val androidTestXRules = "androidx.test:rules:${Versions.androidTestX}"
    val androidTestXOrchestrator = "androidx.test:orchestrator:${Versions.androidTestX}"

    val androidTestXEspressoCore = "androidx.test.espresso:espresso-core:${Versions.androidTestXEspresso}"
    val androidTestXEspressoIntents = "androidx.test.espresso:espresso-intents:${Versions.androidTestXEspresso}"
    val androidTestXEspressoWeb = "androidx.test.espresso:espresso-web:${Versions.androidTestXEspresso}"
    val androidTestXEspressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.androidTestXEspresso}"

    val androidTestXUiAutomator = "androidx.test.uiautomator:uiautomator:${Versions.androidTestXUiAutomator}"

    val androidTestNavigation = "android.arch.navigation:navigation-testing:${Versions.androidNavigation}"

//    val androidTestKakao = "com.agoda.kakao:Kakao:${Versions.androidTestKakao}"
    val androidTestKakao = "com.github.wmontwe:Kakao:${Versions.androidTestKakao}"
}
