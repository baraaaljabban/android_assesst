package com.tawk.github_users.core.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * The dark color scheme for the Tawk theme.
 *
 * @property primary The primary color for dark mode.
 * @property secondary The secondary color for dark mode.
 * @property tertiary The tertiary color for dark mode.
 * @property background The background color for dark mode.
 * @property onSurface The color for text and icons on dark surfaces.
 * @property surface The surface color for dark mode.
 * @property onPrimary The color for text and icons on the primary dark color.
 * @property onBackground The color for text and icons on the dark background.
 */
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = PurpleGrey80,
    onSurface = PurpleGrey80,
    surface = PurpleGrey80,
    onPrimary = Purple80, onBackground = PurpleGrey80,

)
/**
 * Defines the light color scheme for the Tawk theme.
 *
 * @param primary The primary color.
 * @param secondary The secondary color.
 * @param tertiary The tertiary color.
 */
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

)

/**
 * Composable function that sets up the Tawk theme.
 *
 * @param darkTheme Whether to use the dark theme or not. Defaults to the system's dark theme setting.
 * @param dynamicColor Whether to use dynamic colors based on the system settings on Android 12+.
 * @param content The content composable.
 */
@Composable
fun TawkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),

    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
      SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = colorScheme.primary.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
      }
    }

    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
}