package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun EmptyList(
    text: String
){
    ColumnCenter(Modifier.fillMaxSize()){
        TeksNormal(color = Warna.AbuTua, text = text,)
    }
}