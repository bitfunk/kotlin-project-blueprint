/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.android.ui.reuse

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import eu.upwolf.mobile.blueprint.android.R
import eu.upwolf.mobile.blueprint.common.ui.theme.AppTheme
import eu.upwolf.mobile.blueprint.common.ui.theme.AppThemeDimension

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    content: @Composable (PaddingValues) -> Unit
) {
    // val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.statusBars
                ),
                backgroundColor = AppTheme.colorScheme.primary,
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = AppTheme.colorScheme.onPrimary
                    )
                }
            )
        },
        content = content
    )
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
    AppScaffold {
        Text(text = "Hello")
    }
}
