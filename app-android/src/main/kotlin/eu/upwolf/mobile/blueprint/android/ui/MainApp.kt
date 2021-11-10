/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.android.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.ProvideWindowInsets
import eu.upwolf.mobile.blueprint.android.ui.component.counter.Counter
import eu.upwolf.mobile.blueprint.android.ui.component.counter.CounterUi
import eu.upwolf.mobile.blueprint.android.ui.reuse.AppScaffold
import eu.upwolf.mobile.blueprint.android.ui.theme.AppTheme
import eu.upwolf.mobile.blueprint.android.ui.theme.MainTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(finishActivity: () -> Unit = {}) {
    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        MainTheme {
            AppScaffold {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colorScheme.background
                ) {
                    Column {
                        Greeting(name = "Hello")
                        CounterUi(counter = Counter())
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Light preview",
    showBackground = true,
)
@Preview(
    name = "Dark preview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun DefaultPreview() {
    MainApp()
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        color = AppTheme.colorScheme.onBackground
    )
}
