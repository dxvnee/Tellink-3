package org.d3if3121.tellink.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun TeksJudulHitam(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        color = Warna.HitamNormal,
        fontSize = 28.sp,
        fontWeight = FontWeight.ExtraBold,
        modifier = modifier
    )
}

@Composable
fun TeksJudulHitamKecil(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        color = Warna.HitamNormal,
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold,
        modifier = modifier
    )
}