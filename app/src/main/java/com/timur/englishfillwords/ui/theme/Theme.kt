package com.timur.englishfillwords.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Paper = Color(0xFFF6F0E2)
val PaperLight = Color(0xFFFFFBF2)
val Ink = Color(0xFF243029)
val Forest = Color(0xFF2F5D46)
val ForestDark = Color(0xFF1E4231)
val Terracotta = Color(0xFFBE6B45)
val Gold = Color(0xFFC08A2E)

private val LightColors = lightColorScheme(
    primary = Forest,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD7E8DC),
    onPrimaryContainer = ForestDark,
    secondary = Terracotta,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFF4DFD2),
    onSecondaryContainer = Color(0xFF5A2C18),
    tertiary = Gold,
    onTertiary = Color.White,
    background = Paper,
    onBackground = Ink,
    surface = PaperLight,
    onSurface = Ink,
    surfaceVariant = Color(0xFFEAE1CC),
    onSurfaceVariant = Color(0xFF6B7565),
    outline = Color(0xFFC9BFA6),
    outlineVariant = Color(0xFFDDD3BC),
    error = Color(0xFFB3423A)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF8FCCA7),
    onPrimary = Color(0xFF10251A),
    primaryContainer = Color(0xFF2A4A38),
    onPrimaryContainer = Color(0xFFD7E8DC),
    secondary = Color(0xFFE0A06C),
    onSecondary = Color(0xFF3A1E0E),
    tertiary = Color(0xFFE7C078),
    onTertiary = Color(0xFF3A2A05),
    background = Color(0xFF121814),
    onBackground = Color(0xFFE7E7DB),
    surface = Color(0xFF1B241E),
    onSurface = Color(0xFFE7E7DB),
    surfaceVariant = Color(0xFF2A342C),
    onSurfaceVariant = Color(0xFFA9B5A7),
    outline = Color(0xFF465247),
    outlineVariant = Color(0xFF333F36),
    error = Color(0xFFE79A94)
)

private val HalloweenColors = darkColorScheme(
    primary = Color(0xFFFF8A2B),
    onPrimary = Color(0xFF2A1200),
    primaryContainer = Color(0xFF4A2410),
    onPrimaryContainer = Color(0xFFFFD9B8),
    secondary = Color(0xFFB388FF),
    onSecondary = Color(0xFF24103D),
    secondaryContainer = Color(0xFF3A2350),
    onSecondaryContainer = Color(0xFFE8D7FF),
    tertiary = Color(0xFF9BE15D),
    onTertiary = Color(0xFF1A2A08),
    background = Color(0xFF120B18),
    onBackground = Color(0xFFF3E9DC),
    surface = Color(0xFF1E1226),
    onSurface = Color(0xFFF3E9DC),
    surfaceVariant = Color(0xFF2E1B3A),
    onSurfaceVariant = Color(0xFFC9B6D6),
    outline = Color(0xFF6B4A7E),
    outlineVariant = Color(0xFF432B52),
    error = Color(0xFFFF6B6B)
)

private val FillwordsTypography = Typography().let { base ->
    base.copy(
        displaySmall = base.displaySmall.copy(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            lineHeight = 38.sp
        ),
        headlineSmall = base.headlineSmall.copy(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        ),
        titleLarge = base.titleLarge.copy(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        ),
        titleMedium = base.titleMedium.copy(fontWeight = FontWeight.SemiBold),
        titleSmall = base.titleSmall.copy(fontWeight = FontWeight.SemiBold)
    )
}

/** Цвета, которыми окрашиваются найденные слова. */
val WordPalette = listOf(
    Color(0xFF2E7D5B),
    Color(0xFFB4633A),
    Color(0xFF3E6B9E),
    Color(0xFF8A5A9E),
    Color(0xFFB08A2E),
    Color(0xFF2F8A8A),
    Color(0xFFA34B5B),
    Color(0xFF5C7A2E)
)

/** Более светлые оттенки для тёмной и хеллоуинской темы. */
private val WordPaletteDark = listOf(
    Color(0xFF7FD9A8),
    Color(0xFFF0A878),
    Color(0xFF8FB8EE),
    Color(0xFFC9A3E0),
    Color(0xFFEBC873),
    Color(0xFF7FD6D6),
    Color(0xFFEE9AA8),
    Color(0xFFB3D17A)
)

@Composable
fun wordColor(index: Int): Color {
    val palette = if (MaterialTheme.colorScheme.background.luminance() < 0.35f) {
        WordPaletteDark
    } else {
        WordPalette
    }
    return palette[index % palette.size]
}

@Composable
fun EnglishFillwordsTheme(
    mode: ThemeMode = ThemeMode.HALLOWEEN,
    content: @Composable () -> Unit
) {
    val colors = when (mode) {
        ThemeMode.LIGHT -> LightColors
        ThemeMode.DARK -> DarkColors
        ThemeMode.HALLOWEEN -> HalloweenColors
    }
    MaterialTheme(
        colorScheme = colors,
        typography = FillwordsTypography,
        content = content
    )
}
