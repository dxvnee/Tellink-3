package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import org.d3if3121.tellink.ui.theme.Warna


@Composable
fun TeksBold(
    text: String,
    color: Color = Warna.HitamNormal,
    size: TextUnit = 18.sp,
    modifier: Modifier = Modifier,
){
    Text(
        text = text,
        color = color,
        fontSize = size,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}


@Composable
fun TeksBoldTombol(
    text: String
){
    TeksBold(text, Warna.PutihNormal, 16.sp)
}


@Composable
fun TeksNormal(
    text: String,
    color: Color = Warna.HitamNormal,
    size: TextUnit = 16.sp,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        color = color,
        fontSize = size,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}