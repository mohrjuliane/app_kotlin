package fhs.mmt.nma.pixie.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import fhs.mmt.nma.pixie.R


private val Nunito = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.nunito_light,
            weight = FontWeight.Light,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.nunito_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.nunito_semi_bold,
            weight = FontWeight.SemiBold,
            style = FontStyle.Normal
        ),
    )
)

val MyTypography = Typography(
    h1 = TextStyle(fontSize = 18.sp, fontFamily = Nunito, fontWeight = FontWeight.Bold, letterSpacing = 0.sp),
    h2 = TextStyle(fontSize = 14.sp, fontFamily = Nunito, fontWeight = FontWeight.Bold, letterSpacing = 0.15.sp),
    subtitle1 = TextStyle(fontSize = 16.sp, fontFamily = Nunito, fontWeight = FontWeight.Light, letterSpacing = 0.sp),
    body1 = TextStyle(fontSize = 14.sp, fontFamily = Nunito, fontWeight = FontWeight.Light, letterSpacing = 0.sp),
    body2 = TextStyle(fontSize = 12.sp, fontFamily = Nunito, fontWeight = FontWeight.Light, letterSpacing = 0.sp),
    button = TextStyle(fontSize = 14.sp, fontFamily = Nunito, fontWeight = FontWeight.SemiBold, letterSpacing = 1.sp),
    caption = TextStyle(fontSize = 12.sp, fontFamily = Nunito, fontWeight = FontWeight.SemiBold, letterSpacing = 0.sp),
)


