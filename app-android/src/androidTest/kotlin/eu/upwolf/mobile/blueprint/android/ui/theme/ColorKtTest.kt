/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.android.ui.theme

import androidx.compose.ui.graphics.Color
import junit.framework.Assert.assertEquals
import org.junit.Test

class ColorKtTest {

    @Test
    fun testRgbaToArgbConversion() {
        // Given
        val rgbaColor = 0x1664C0FF
        val expected = Color(0xFF1664C0)

        // When
        val result = fromRgba(rgbaColor)

        // Then
        assertEquals(
            expected,
            result
        )
    }
}
