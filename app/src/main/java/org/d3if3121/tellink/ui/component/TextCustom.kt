package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun TeksJudulMerah(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        color = Warna.MerahNormal,
        fontSize = 21.sp,
        fontWeight = FontWeight.ExtraBold,
        modifier = modifier
    )
}