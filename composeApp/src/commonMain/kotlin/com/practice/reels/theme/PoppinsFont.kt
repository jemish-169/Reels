package com.practice.reels.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import reels.composeapp.generated.resources.Res
import reels.composeapp.generated.resources.poppins_bold
import reels.composeapp.generated.resources.poppins_bold_italic
import reels.composeapp.generated.resources.poppins_extra_bold
import reels.composeapp.generated.resources.poppins_extra_bold_italic
import reels.composeapp.generated.resources.poppins_italic
import reels.composeapp.generated.resources.poppins_light
import reels.composeapp.generated.resources.poppins_light_italic
import reels.composeapp.generated.resources.poppins_medium
import reels.composeapp.generated.resources.poppins_medium_italic
import reels.composeapp.generated.resources.poppins_regular
import reels.composeapp.generated.resources.poppins_semi_bold
import reels.composeapp.generated.resources.poppins_semi_bold_italic

@Composable
fun poppinsFont() = FontFamily(
    Font(Res.font.poppins_light, FontWeight.Light),
    Font(Res.font.poppins_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(Res.font.poppins_regular, FontWeight.Normal),
    Font(Res.font.poppins_italic, FontWeight.Normal, FontStyle.Italic),
    Font(Res.font.poppins_medium, FontWeight.Medium),
    Font(Res.font.poppins_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(Res.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(Res.font.poppins_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(Res.font.poppins_bold, FontWeight.Bold),
    Font(Res.font.poppins_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(Res.font.poppins_extra_bold, FontWeight.ExtraBold),
    Font(Res.font.poppins_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic)
)
