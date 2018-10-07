package de.wolfmontwe.blueprint.android.page

import androidx.test.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

abstract class BasePage {

    val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())!!


    init {
        this.waitForPage()
    }


    abstract fun waitForPage()

    fun waitByResource(resourceName: String) {
        device.wait(Until.hasObject(By.res(resourceName)), TIMEOUT)
    }


    companion object {
        const val TIMEOUT = 1000 * 60L

        const val TIMEOUT_SHORT = 1000 * 5L
    }
}
