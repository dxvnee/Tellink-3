package org.d3if3121.tellink.ui.component

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
fun TeksNormal(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = Warna.HitamNormal,
    size: TextUnit = 14.sp,
    lineHeight: TextUnit = size * 1.2f
){
    Text(
        text = text,
        color = color,
        fontSize = size,
        fontWeight = FontWeight.Normal,
        textAlign = textAlign,
        modifier = modifier,
        lineHeight = lineHeight
    )
}

@Composable
fun TeksBold(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = Warna.HitamNormal,
    size: TextUnit = 16.sp,
){
    Text(
        text = text,
        color = color,
        fontSize = size,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun TeksBoldTombol(
    text: String,
    color: Color = Warna.PutihNormal,
    size: Int = 15
){
    TeksBold(text, color = color, size = size.sp)
}



@Composable
fun TeksNormalAbu(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Warna.AbuTua,
    size: TextUnit = 14.sp,
){
    Text(
        text = text,
        color = color,
        fontSize = size,
        fontWeight = FontWeight.Normal,
        modifier = modifier
    )
}

@Composable
fun TeksBoldAbu(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Warna.AbuTua,
    size: TextUnit = 14.sp,
){
    Text(
        text = text,
        color = color,
        fontSize = size,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.ExtraBold,
        modifier = modifier
    )
}

@Composable
fun TeksNormalMerah(
    text: String,
    color: Color = Warna.MerahNormal,
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

@Composable
fun TeksBoldMerah(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = Warna.MerahNormal,
    size: TextUnit = 16.sp,
){
    Text(
        text = text,
        color = color,
        fontSize = size,
        fontWeight = FontWeight.ExtraBold,
        textAlign = textAlign,
        modifier = modifier
    )
}