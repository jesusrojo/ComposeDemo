package com.jesusrojo.composedemo.toolbar.theme

import androidx.annotation.FontRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.jesusrojo.composedemo.R

val IGO_YELLOW = Color(0xFFfff8bc)
val IGO_STRONG_YELLOW = Color(0xFFffe414)
val IGO_TEXT_COLOR = Color(0xFF072A40)
val IGO_TEXT_GRAY = Color(0xFF687780)
val IGO_LIGHT_YELLOW = Color(0xFFFFFDEA)

object Typography {
    fun kronaRegular(fontSize: TextUnit, textDecoration: TextDecoration? = null) = TextStyle(
        color = IGO_TEXT_COLOR,
        fontFamily = FontFamily(Font(R.font.krona_one_regular, FontWeight.Normal)),
        fontSize = fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.sp,
        textDecoration = textDecoration
    )

    fun robotoRegular(fontSize: TextUnit, textDecoration: TextDecoration? = null) =
        roboto(R.font.roboto_regular, fontSize, IGO_TEXT_COLOR, textDecoration)


    fun robotoMedium(fontSize: TextUnit, textDecoration: TextDecoration? = null) =
        roboto(R.font.roboto_medium, fontSize, IGO_TEXT_COLOR, textDecoration)

    fun robotoBold(fontSize: TextUnit, textDecoration: TextDecoration? = null) =
        roboto(R.font.roboto_bold, fontSize, IGO_TEXT_COLOR, textDecoration)

    fun robotoRegularGray(fontSize: TextUnit, textDecoration: TextDecoration? = null) =
        roboto(R.font.roboto_regular, fontSize, IGO_TEXT_GRAY, textDecoration)

    fun robotoMediumGray(fontSize: TextUnit, textDecoration: TextDecoration? = null) =
        roboto(R.font.roboto_medium, fontSize, IGO_TEXT_GRAY, textDecoration)

    fun robotoBoldGray(fontSize: TextUnit, textDecoration: TextDecoration? = null) =
        roboto(R.font.roboto_bold, fontSize, IGO_TEXT_GRAY, textDecoration)

    private fun roboto(
        @FontRes font: Int,
        fontSize: TextUnit = 22.sp,
        color: Color = IGO_TEXT_COLOR,
        textDecoration: TextDecoration? = null
    ) = TextStyle(
        color = color,
        fontFamily = FontFamily(Font(font, FontWeight.Normal)),
        fontSize = fontSize,
        lineHeight = 22.sp,
        textDecoration = textDecoration
    )
}
