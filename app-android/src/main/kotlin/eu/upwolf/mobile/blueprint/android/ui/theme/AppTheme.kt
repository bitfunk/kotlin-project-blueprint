package eu.upwolf.mobile.blueprint.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun BlueprintTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkAppColorScheme
    } else {
        LightAppColorScheme
    }

    AppTheme(
        colorScheme = colorScheme,
        content = content,
        darkTheme = darkTheme
    )
}

object AppTheme {
    val colorScheme: AppColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val dimension: AppDimension
        @Composable
        @ReadOnlyComposable
        get() = LocalDimension.current
}

@Composable
fun AppTheme(
    colorScheme: AppColorScheme = AppTheme.colorScheme,
    typography: AppTypography = AppTheme.typography,
    dimension: AppDimension = AppTheme.dimension,
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent
        )
    }

    ProvideAppTheme(
        colorScheme,
        dimension,
        typography,
    ) {
        MaterialTheme(
            colorScheme = DebugColorScheme,
            // shapes = Shapes,
            content = content
        )
    }
}

@Composable
fun ProvideAppTheme(
    colorScheme: AppColorScheme,
    dimension: AppDimension,
    typography: AppTypography,
    content: @Composable () -> Unit
) {
    val rememberedColorScheme = remember {
        colorScheme.copy()
    }.apply {
        update(colorScheme)
    }

    CompositionLocalProvider(
        LocalColorScheme provides rememberedColorScheme,
        LocalDimension provides dimension,
        LocalTypography provides typography,
        content = content
    )
}
