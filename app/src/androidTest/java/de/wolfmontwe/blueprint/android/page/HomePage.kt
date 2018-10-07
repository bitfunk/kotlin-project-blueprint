package de.wolfmontwe.blueprint.android.page

import com.agoda.kakao.KButton
import com.agoda.kakao.KView
import com.agoda.kakao.Screen
import de.wolfmontwe.blueprint.android.R

class HomePage : BasePage() {


    private val screen = HomeScreen()


    override fun waitForPage() {
        waitByResource("de.wolfmontwe.blueprint.android:id/home_constraint")
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

        val logoutButton = KButton { withId(R.id.home_logout_button) }
    }

}
