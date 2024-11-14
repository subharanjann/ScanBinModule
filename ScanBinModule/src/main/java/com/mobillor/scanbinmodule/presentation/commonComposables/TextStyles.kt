package com.mobillor.scanbinmodule.presentation.commonComposables
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mobillor.scanbinmodule.R


import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.darkGrey
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.darkMediumGrey
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.evenLighterRedStuff
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.grey
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.primaryColor
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.primaryColorAccent
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.secondaryColor
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.white



//fonts
val ComfortaaLight = FontFamily(
    Font(R.font.comfortaa_light, FontWeight.Light)
)

//styles
object TextStyles {
    val normalTextStyle = TextStyle(
        fontSize = 13.sp,
        color = darkGrey
    )
    val normalTextStyleCustomLoadShipment = TextStyle(
        fontSize = 11.sp,
        color = darkGrey
    )
    val titleTextStyleCustomLoadShipment = TextStyle(
        fontSize = 13.sp,
        color = primaryColor,
        fontWeight = FontWeight.Bold
    )
    val smallMediumNormalTextStyleB = TextStyle(
        fontSize = 12.sp,
        color = darkMediumGrey
    )
    val normalLightTextStyle = TextStyle(
        fontSize = 13.sp,
        color = grey
    )

    val smallNormalTextStyle = TextStyle(
        fontSize = 9.sp,
        color = darkMediumGrey
    )

    val mediumBoldTextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    val smallMediumGreyTextStyle = TextStyle(
        fontSize = 12.sp,
        color = secondaryColor
    )
    val smallMediumNormalTextStyle = TextStyle(
        fontSize = 12.sp,
        color = darkMediumGrey
    )
    val smallMediumRedTextStyle = TextStyle(
        fontSize = 12.sp,
        color = evenLighterRedStuff
    )

    val smallLightColoredNormalTextStyle = TextStyle(
        fontSize = 12.sp,
        color = primaryColorAccent
    )
    val smallMediumLightColoredNormalTextStyle = TextStyle(
        fontSize = 12.sp,
        color = primaryColorAccent
    )

    val smallNormalGreyTextStyle = TextStyle(
        fontSize = 10.sp,
        color = secondaryColor
    )
    val smallNormalDarkGreyTextStyle = TextStyle(
        fontSize = 11.sp,
        color = darkMediumGrey
    )
    val extraSmallNormalDarkGreyTextStyle = TextStyle(
        fontSize = 9.sp,
        color = darkMediumGrey
    )

    val normalTextStyleBold = TextStyle(
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
    val normalWhiteTextStyleBold = TextStyle(
        fontSize = 16.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )

    val normalTextStyleRed = TextStyle(
        fontSize = 14.sp,
        color = primaryColor
    )

    val titleTextStyle = TextStyle(
        fontSize = 18.sp,
        color = darkGrey,
        textAlign = TextAlign.Center
    )

    val whiteTitleTextStyle = TextStyle(
        fontSize = 18.sp,
        color = white,
        textAlign = TextAlign.Center
    )

    val subTextStyle = TextStyle(
        fontSize = 12.sp,
        color = secondaryColor
    )

    val subTextDarkStyle = TextStyle(
        fontSize = 12.sp,
        color = darkMediumGrey
    )

    val highlightedTextStyle = TextStyle(
        fontSize = 14.sp,
        color = primaryColorAccent,
        textAlign = TextAlign.Center
    )

    val semiHighlightedTextStyle = TextStyle(
        fontSize = 12.sp,
        color = primaryColor,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )

    val wildcardTextStyle = TextStyle(
        fontSize = 14.sp,
        color = darkGrey,
        textAlign = TextAlign.Center,
        fontFamily = ComfortaaLight
    )
    val bigWildcardTextStyle = TextStyle(
        fontSize = 24.sp,
        color = darkGrey,
        textAlign = TextAlign.Center,
        fontFamily = ComfortaaLight
    )
    val whiteWildcardTextStyle = TextStyle(
        fontSize = 24.sp,
        color = white,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontFamily = ComfortaaLight
    )
    val whiteWildcardExtraBigTextStyle = TextStyle(
        fontSize = 28.sp,
        color = white,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontFamily = ComfortaaLight
    )
    val smallWildcardTextStyle = TextStyle(
        fontSize = 10.sp,
        color = darkGrey,
        textAlign = TextAlign.Center,
        fontFamily = ComfortaaLight
    )
}