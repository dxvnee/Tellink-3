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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3121.tellink.ui.component.ButtonMerah
import org.d3if3121.tellink.ui.component.TeksBoldTombol
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun AuthTombol(
    onClickButton: () -> Unit,
    onClickText: (Int) -> Unit,

    textTombol: String,
    textTeks1: String,
    textTeks2: String,
){
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxHeight()
    ) {
        ButtonMerah(
            onClick = onClickButton,
            content = { TeksBoldTombol(textTombol) },
            modifier = Modifier.fillMaxWidth().padding(top = 25.dp, bottom = 4.dp).size(46.dp),
        )

        TeksSwitchPage(textTeks1, textTeks2, onClickText)
    }
}

@Composable
fun TeksSwitchPage(
    text: String,
    text2: String,
    onClick: (Int) -> Unit
){
    Row(verticalAlignment = Alignment.CenterVertically){
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight(500)
        )
        ClickableText(
            text = AnnotatedString(text2),
            onClick = onClick,
            style = TextStyle.Default.copy(
                Warna.MerahNormal,
                fontSize = 12.sp,
                fontWeight = FontWeight(500)
            )
        )
    }
}
