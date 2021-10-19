package fhs.mmt.nma.pixie.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun PixieTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    MaterialTheme(
        content = content
    )
}

//LightVersion
val primary = Color(0xFF6200EE)
val primaryVariant = Color(0xFF3700B3)
val secondary = Color(0xFF36DAC5)
val onPrimary = Color(0xFFFFFFFF)
val background = Color(0xFFFFFFFF)
val onBackground = Color(0xFF000000)
val surface = Color(0xFFFFFFFF)
val onSurface = Color(0xFF000000)



