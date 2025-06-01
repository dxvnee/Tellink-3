package org.d3if3121.tellink.ui.theme

import android.widget.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable

object CustomButtonColors {

    val MerahPutih : ButtonColors
        @Composable
        get() = ButtonColors(
            containerColor = Warna.PutihNormal,
            contentColor = Warna.MerahNormal,
            disabledContentColor = Warna.MerahNormal,
            disabledContainerColor = Warna.MerahNormal
        )

    val PutihMerah : ButtonColors
        @Composable
        get() =  ButtonColors(
            containerColor = Warna.MerahNormal,
            contentColor = Warna.PutihNormal,
            disabledContentColor = Warna.MerahNormal,
            disabledContainerColor = Warna.PutihNormal
        )

}