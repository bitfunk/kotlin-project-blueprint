/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.android.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import eu.upwolf.mobile.blueprint.android.R
import eu.upwolf.mobile.blueprint.android.ui.theme.AppTheme
import eu.upwolf.mobile.blueprint.android.ui.theme.BlueprintTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlueprintApp(finishActivity: () -> Unit = {}) {
    val navController = rememberNavController()

    BlueprintTheme {
        ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        contentPadding = rememberInsetsPaddingValues(
                            insets = LocalWindowInsets.current.statusBars
                        ),
                        backgroundColor = AppTheme.colorScheme.primary,
                        title = {
                            Text(
                                text = stringResource(id = R.string.blueprint_app_name),
                                color = AppTheme.colorScheme.onPrimary
                            )
                        }
                    )
                }
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colorScheme.background
                ) {
                    Greeting(name = "Hello")
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
    BlueprintApp()
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        color = AppTheme.colorScheme.onBackground
    )
}
