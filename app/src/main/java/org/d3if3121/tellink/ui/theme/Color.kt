package org.d3if3121.tellink.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.d3if3121.tellink.ui.animation.AnimationFade

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
object Warna {
    val MerahNormal = Color(0xFFE40000)
    val MerahTua = Color(0xFFC7001E)
    val PutihNormal = Color(0xFFFFFFFF)
    val PutihGelap = Color(0xFFF4F4F4)
    val HitamNormal = Color(0xFF000000)
    val AbuNormal = Color(0xFFC3C3C3)
    val AbuTua = Color(0xFFB5B5B5)
    val AbuMuda = Color(0xFFE6E6E6)
    val Hijau = Color(0xFF83FF00)
}

@Composable
fun redButtonColor(): ButtonColors{
    return ButtonDefaults.buttonColors(
        containerColor = Warna.MerahNormal,
        contentColor = Warna.PutihNormal
    )
}

@Composable
fun whiteButtonColor(): ButtonColors{
    return ButtonDefaults.buttonColors(
        containerColor = Warna.AbuMuda,
        contentColor = Warna.MerahNormal
    )
}

@Composable
fun ChangeNavColor(visible: Boolean){
    if(visible){
        DynamicNavigationBarColor(backgroundColor = Warna.HitamNormal.copy(alpha = 0.4f))
    } else {
        DynamicNavigationBarColor(backgroundColor = Warna.PutihGelap)
    }
}
