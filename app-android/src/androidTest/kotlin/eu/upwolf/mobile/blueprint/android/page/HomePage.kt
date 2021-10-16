/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.android.page

import eu.upwolf.mobile.blueprint.android.R
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

class HomePage : BasePage() {

    private val screen = HomeScreen()

    override fun waitForPage() {
        waitByResource("eu.upwolf.mobile.blueprint.android:id/home_constraint")
    }

    fun doLogout() {
        screen.logoutButton { click() }
    }

    fun isVisible(): HomePage {
        screen.root { isDisplayed() }

        return this
    }

    class HomeScreen : Screen<HomeScreen>() {
        val root = KView { withId(R.id.home_constraint) }

        val logoutButton = KButton { withId(R.id.home_button) }
    }
}
