@file:Suppress("DEPRECATION")

package org.d3if3121.tellink.ui.screen.auth.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3121.tellink.ui.component.BorderBox
import org.d3if3121.tellink.ui.component.ButtonMerah
import org.d3if3121.tellink.ui.component.IconNormalWithBox
import org.d3if3121.tellink.ui.component.SpaceWidth
import org.d3if3121.tellink.ui.component.TeksBoldTombol
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun TombolTeksBawah(
    onClickButton: () -> Unit,
    onClickText: (Int) -> Unit,

    textTombol: String,
    textTeks1: String = "",
    textTeks2: String,

    text2Color: Color = Warna.MerahNormal,
){
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxHeight()
    ) {
        ButtonMerah(
            onClick = onClickButton,
            content = { TeksBoldTombol(textTombol) },
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp).size(39.dp),
        )

        TeksSwitchPage(textTeks1, textTeks2, text2Color, onClick = onClickText)
    }
}

@Composable
fun TeksSwitchPage(
    text: String = "",
    text2: String,
    text2Color: Color,
    text1Color: Color = Warna.HitamNormal,
    textSize: Int = 12,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    onClick: (Int) -> Unit,
){
    Row(verticalAlignment = verticalAlignment){
        Text(
            text = text,
            fontSize = textSize.sp,
            fontWeight = FontWeight(500),
            style = TextStyle.Default.copy(
                text1Color,
                fontSize = textSize.sp,
                fontWeight = FontWeight(500)
            )
        )

        ClickableText(
            text = AnnotatedString(text2),
            onClick = onClick,
            style = TextStyle.Default.copy(
                text2Color,
                fontSize = textSize.sp,
                fontWeight = FontWeight(500)
            )
        )

    }
}

@Composable
fun TeksSwitchPageWithIcon(
    imageVector: ImageVector,
    color: Color,
    colorIcon: Color,
    size: Dp,
    text1: String,
    text2: String,
    text1Color: Color,
    text2Color: Color,
    onTextClick: () -> Unit
){
    Row {
        IconNormalWithBox(
            imageVector = imageVector,
            color = color,
            colorIcon = colorIcon,
            size = size,
        )

        SpaceWidth(7)

        TeksSwitchPage(
            text = text1,
            text2 = text2,
            text2Color = text2Color,
            text1Color = text1Color,
            textSize = 13,
            verticalAlignment = Alignment.Bottom
        ){ onTextClick() }
    }

}
